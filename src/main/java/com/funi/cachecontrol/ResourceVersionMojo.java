package com.funi.cachecontrol;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 给指定的资源文件打上版本号
 *
 * @author Ternence
 * @goal version
 * @phase prepare-package
 * @date 2015年1月15日
 */
public class ResourceVersionMojo extends ResourceMojo {

    public ResourceVersionMojo() {
        super();
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        super.execute();
        try {
            versionPageUrl();
        } catch (IOException e) {
            throw new MojoExecutionException("Version url exception:", e);
        }
    }

    /**
     *
     * @throws IOException
     */
    private void versionPageUrl() throws IOException {

        for (String pageDir : pageDirs) {
            String absDir = webappDir + File.separator + pageDir;
            File f = new File(absDir);
            iterateDir(f);
        }
    }

    // 遍历处理
    private void iterateDir(File file) throws IOException {
        if (file.isDirectory()) {
            File[] fs = file.listFiles();
            for (File f : fs) {
                iterateDir(f);
            }
        } else {
            String fileName = file.getName();
            String sufix = FilenameUtils.getExtension(fileName);
            if (StringUtils.isNotEmpty(sufix) && ArrayUtils.contains(suffixs, sufix.toLowerCase())) {
                log.info("Versioning file:" + file.getAbsolutePath());
                String pageText = FileUtils.readFileToString(file, "UTF-8");
                pageText = handleFile(pageText);
                FileUtils.writeStringToFile(file, pageText, "UTF-8");
            }
        }
    }

    public String handleFile(String page) {
        page = resolveJsRefer(page);
        page = resolveCssRefer(page);
        page = resolveImageRefer(page);
        return page;
    }

    public String resolveJsRefer(String page) {
        Matcher matcher = JS_PATTERN.matcher(page);
        StringBuffer sb = new StringBuffer();

        while (true) {
            if (!matcher.find()) {
                break;
            }
            String pfull = matcher.group();
            String jsdirPart = matcher.group(1);
            pfull = pfull.substring(0, matcher.start(1) - matcher.start()) + versionStaticURL(jsdirPart.trim()) + pfull.charAt(pfull.length() - 1);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(pfull));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public String resolveImageRefer(String page) {
        Matcher matcher = IMAGES_PATTERN.matcher(page);
        StringBuffer sb = new StringBuffer();

        while (true) {
            if (!matcher.find()) {
                break;
            }
            String pfull = matcher.group();

            String jsdirPart = matcher.group(1);
            pfull = pfull.substring(0, matcher.start(1) - matcher.start()) + versionStaticURL(jsdirPart.trim()) + pfull.charAt(pfull.length() - 1);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(pfull));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public String resolveCssRefer(String page) {

        Matcher matcher = CSS_PATTERN.matcher(page);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String pfull = matcher.group();
            String cssPath = matcher.group(1);
            pfull = pfull.substring(0, matcher.start(1) - matcher.start()) + versionStaticURL(cssPath.trim()) + pfull.charAt(pfull.length() - 1);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(pfull));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String setParam(String url, String paramname, String paramvalue) {
        if (StringUtils.isBlank(url)) {
            return "";
        }

        String reg1 = "(" + paramname + "=.*?)(?=(&.*|$))";
        if (Pattern.compile(reg1).matcher(url).find()) {
            return url.replaceFirst(reg1, paramname + "=" + paramvalue);
        }
        if (url.contains("?")) {
            return url + "&" + paramname + "=" + paramvalue;
        }
        return url + "?" + paramname + "=" + paramvalue;
    }

    private String versionStaticURL(String addr) {
        String newURL = addr.trim();
        if (StringUtils.isNotBlank(resourcesURL)) {
            if (newURL.startsWith("/")) {
                newURL = resourcesURL + newURL;
            } else if (newURL.indexOf("http://") != -1) {

            } else {
                newURL = resourcesURL + "/" + newURL;
            }
        }
        return setParam(newURL, "v", version);
    }
}
