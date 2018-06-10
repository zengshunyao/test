package category.xml;

import java.io.*;
import java.net.URL;

/**
 * Created by Lenovo on 2015/5/22.
 */

/**
 * 此类用于获得文件路径的demo
 */
public class GetFilePath {
    public static void main(String[] args) {
//        get4Web();
        get4File();
    }

    public static void get4Web() {
        System.out.println(GetFilePath.class.getClassLoader().getResource("").getPath());
        System.out.println(GetFilePath.class.getClassLoader().getResource("").getPath());
        //得到的是 ClassPath的绝对URI路径。
        //如：/D:/jboss-4.2.2.GA/server/default/deploy/hp.war/WEB-INF/classes/

        System.out.println(System.getProperty("user.dir"));

        System.out.println(GetFilePath.class.getClassLoader().getResource(".").getPath());
        //得到的是 项目的绝对路径。
    }

    public static void get4File() {
        System.out.println(System.getProperty("java.class.path"));
        //方式一
        System.out.println(System.getProperty("user.dir"));
        //方式二
        File directory = new File("");//设定为当前文件夹
        try {
            System.out.println(directory.getCanonicalPath());//获取标准的路径
            System.out.println(directory.getAbsolutePath());//获取绝对路径
        } catch (Exception e) {
            e.printStackTrace();
        }
        //方式三
        System.out.println(GetFilePath.class.getResource("/"));
        System.out.println(GetFilePath.class.getResource(""));
        //方式4
        System.out.println(GetFilePath.class.getClassLoader().getResource(""));
        System.out.println(GetFilePath.class.getClassLoader().getResource("books.xml"));
    }

    /**
     * @param name 文件名  不包含路径
     */
    public static String getSrcPath(String name) {
        String result = null;
        URL urlpath = GetFilePath.class.getClassLoader().getResource(name);
        String path = urlpath.toString();
        //remove the head "file:",if it exists
        if (path.startsWith("file")) {
            path = path.substring(5);
        }
        path.replace("/", File.separator);
        result = path;
        return result;
    }

    // filename 文件名 不包含路径
    // ...args  文件夹名      可以输入多个文件夹名参数
    public static String getParallelPath(String filename, String... args) {
        String pre = System.getProperty("user.dir");
        String path = pre;
        for (String arg : args) {
            path += File.separator + arg;
        }
        path += File.separator + filename;
        if (path.startsWith("file")) {
            path = path.substring(5);
        }
        path.replace("/", File.separator);
        return path;
    }

    public static void readFile(String filepath) {
        File file = new File(filepath);
        try {
            InputStreamReader sr = new InputStreamReader(new FileInputStream(file));
            BufferedReader br = new BufferedReader(sr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPackagePath(String filename) {
        String result = null;
        URL urlpath = GetFilePath.class.getResource("");
        String path = urlpath.toString();
        if (path.startsWith("file")) {
            path = path.substring(5);
        }
        path.replace("/", File.separator);
        result = path + filename;
        return result;
    }

    //获取WebRoot目录
    public static String getWebRootPath() {
        URL urlpath = GetFilePath.class.getResource("");
        String path = urlpath.toString();
        if (path.startsWith("file")) {
            path = path.substring(5);
        }
        if (path.indexOf("WEB-INF") > 0) {
            path = path.substring(0, path.indexOf("WEB-INF") - 1);
        }
        path.replace("/", File.separator);
        return path;
    }

    //webroot  WebRoot目录
    //filename  文件名
    //...args   文件名所在文件夹，多个参数输入
    public static String getWebRootFilepath(String webroot, String filename, String... args) {
        String pre = webroot;
        String path = pre;
        for (String arg : args) {
            path += File.separator + arg;
        }
        path += File.separator + filename;
        if (path.startsWith("file")) {
            path = path.substring(5);
        }
        path.replace("/", File.separator);
        return path;
    }
}
