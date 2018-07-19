package com.funi.cachecontrol;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 将resourcesDirs中指定的资源目录打成war包
 *
 * @goal package
 * @phase prepare-package
 * @author Ternence
 * @date 2015年1月15日
 */
public class ResourcePackageMojo extends ResourceMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        super.execute();
        packageResourceToZip();
    }

    /**
     *
     */
    private void packageResourceToZip() {
        try {
            FileOutputStream dest = new FileOutputStream(target
                    + File.separator + artifactId + "-resources-" + version
                    + ".war");
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                    dest));
            byte[] buffere = new byte[8192];
            int length;
            BufferedInputStream bis;

            for (String resourcesDir : resourcesDirs) {
                String absDir = webappDir + File.separator + resourcesDir;
                List<?> fileList = loadFilename(new File(absDir));
                for (int i = 0; i < fileList.size(); i++) {
                    File file = (File) fileList.get(i);
                    out.putNextEntry(new ZipEntry(this.getEntryName(file)));
                    bis = new BufferedInputStream(new FileInputStream(file));
                    log.info("Packaging file：" + file.getAbsolutePath());
                    while (true) {
                        length = bis.read(buffere);
                        if (length == -1)
                            break;
                        out.write(buffere, 0, length);
                    }
                    bis.close();
                    out.closeEntry();
                }
            }
            out.close();
        } catch (Exception e) {
            log.error(e);
        }
    }

    /**
     *
     * @param file
     * @return
     */
    private String getEntryName(File file) {
        File base = new File(webappDir);
        String ret = file.getName();
        File real = file;
        while (true) {
            real = real.getParentFile();
            if (real == null)
                break;
            if (real.equals(base))
                break;
            else {
                ret = real.getName() + "/" + ret;
            }
        }
        return ret;
    }

    /**
     * 递归获得该文件下所有文件名(不包括目录名)
     *
     * @param file
     * @return
     */
    private List<File> loadFilename(File file) {
        List<File> filenameList = new ArrayList<File>();
        if (file.isFile()) {
            filenameList.add(file);
        }
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                filenameList.addAll(loadFilename(f));
            }
        }
        return filenameList;
    }

}
