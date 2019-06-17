package encoding.ansiutf8;

/**********************************************************************   
 * &lt;p&gt;文件名：Main.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2019/1/9 22:33 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Collection;

public class Main {

    static String sDirectory = "E:\\ZSY\\IdeaProjects\\spring-framework-3.2.6\\spring-framework-3.2.6.RELEASE";
    //    // 转为UTF-8编码格式源码路径
    static String dDirectory = "E:\\ZSY\\IdeaProjects\\spring-framework-3.2.6\\spring-framework-3.2.6";

    public static void main(String[] args) {
        String sDirectory = "E:\\ZSY\\IdeaProjects\\2\\spring-framework-3.2.6";
        // 转为UTF-8编码格式源码路径
        String dDirectory = "E:\\ZSY\\IdeaProjects\\2\\spring-framework-3.2.6new";
//        Main main = new Main();
//        try {
//            main.readerFile(main.sDirectory);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        convert(sDirectory, dDirectory);
    }

    public void readerFile(String filePath) throws IOException {
        if ("".equals(filePath) || null == filePath) {
            return;
        }

        File f = new File(filePath);
        if (f.isDirectory()) {
            String[] child = f.list();
            for (int i = 0; i < child.length; i++) {
                String path = f.getAbsolutePath() + File.separator;
                String newPath = path.replace(this.sDirectory, this.dDirectory);
                child[i] = path + child[i];
                File c = new File(child[i]);
                String newFile = child[i].replace(this.sDirectory, this.dDirectory);

//                System.out.println("旧路径：" + path);
//                System.out.println("新路径：" + newPath);

                File newP = new File(newPath);
                if (!newP.exists())
                    newP.mkdir();

                if (c.isFile()) {
//                    System.out.println("旧文件：" + child[i]);
//                    System.out.println("新文件：" + newFile);
                    // Charset US-ASCII ISO-8859-1 UTF-8 UTF-16BE UTF-16LE UTF-16
                    BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(c), "GBK"));
                    // BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(c)));
                    File newF = new File(newFile);
                    newF.createNewFile();
                    // BufferedWriter w = new BufferedWriter(new
                    // OutputStreamWriter(new FileOutputStream(newF), "UTF-8"));
                    BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newF)));
                    // BufferedWriter w = new BufferedWriter(new FileWriter(newFile));
                    String lineText = null;
                    while ((lineText = r.readLine()) != null) {
                        // String temp = new String(lineText.getBytes("ISO-8859-1"), "UTF-8");
                        w.write(lineText);
                        w.newLine();
                    }
                    w.close();
                    r.close();
                } else {
                    readerFile(child[i]);
                }
            }
        }
    }

    /**
     * GBK->UTf-8
     *
     * @param srcDirPath
     * @param utf8DirPath
     */
    public static void convert(String srcDirPath, String utf8DirPath) {
        try {
            // 获取所有java文件
            Collection<File> javaGbkFileCol = FileUtils.listFiles(new File(srcDirPath), new String[]{"java"}, true);

            for (File javaGbkFile : javaGbkFileCol) {
                System.out.println(javaGbkFile);
                // UTF8格式文件路径
                String utf8FilePath = utf8DirPath + javaGbkFile.getAbsolutePath().substring(srcDirPath.length());
                // 使用GBK读取数据，然后用UTF-8写入数据
                FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils.readLines(javaGbkFile, "GBK"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
