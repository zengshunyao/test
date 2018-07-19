package com.funi.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

@Mojo(name = "StatisticFile", defaultPhase = LifecyclePhase.COMPILE)
public class StatisticFile extends AbstractMojo {


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("StatisticFile drive...");

        String path = "L:\\项目\\1\\src";
        List<File> list = this.totalFiles(path);
        System.out.println("文件数量：" + list.size());

        //统计代码行数
        Integer row = 0;
        for (File file : list) {
            System.out.println(file.getName());
            FileReader fr = null;//创建文件输入流
            try {
                fr = new FileReader(file);
                BufferedReader in = new BufferedReader(fr);//包装文件输入流，可整行读取
                String line = "";
                while (null != (line = in.readLine())) {
                    row++;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("代码行数：" + row);
    }

    /**
     * 统计文件列表
     *
     * @param path
     * @return
     */
    public static List<File> totalFiles(String path) {
        List<File> files = new LinkedList<>();
        File file = new File(path);
        File[] files2 = file.listFiles();
        for (File file3 : files2) {
            if (file3.isFile()) {
                files.add(file3);
            } else {
                files.addAll(files.size(), totalFiles(file3.getPath()));
            }
        }
        return files;
    }
}
