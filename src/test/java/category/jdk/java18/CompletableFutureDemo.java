package category.jdk.java18;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**********************************************************************
 * &lt;p&gt;文件名：CompletableFutureDemo.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2019/4/10 19:59 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class CompletableFutureDemo {

    /**
     * see {@link CompletableFuture}
     *
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1.完成操作
//        CompletableFuture<String> completableFuture = new CompletableFuture<String>();
//
//        completableFuture.complete("hello World");
//
//        String value = completableFuture.get();
//
//        System.out.println(value);

        //2.异步操作,阻塞操作
//        CompletableFuture asyncCompletableFuture = CompletableFuture.runAsync(() -> {
//            System.out.println(String.format("Hello,World;[Thread:%s]", Thread.currentThread().getName()));
//        });
//        asyncCompletableFuture.get();
//        System.out.println(String.format("Starting....[Thread:%s]", Thread.currentThread().getName()));

        //3.异步,阻塞操作
//        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
//            String value = String.format("Hello,World;[Thread:%s]", Thread.currentThread().getName());
//            System.out.println(value);
//            return value;
//        });
//
//        System.out.println(completableFuture.get());
//        System.out.println(String.format("Starting....[Thread:%s]", Thread.currentThread().getName()));

        //4.合并操作
        CompletableFuture combineFuture
                ;
        combineFuture = CompletableFuture.supplyAsync(() -> {
            String value = "Hello,World;";
            System.out.println(String.format("[Thread:%s]", Thread.currentThread().getName()));
            return value;
        }).thenApply(value -> {
            System.out.println(String.format("[Thread:%s]", Thread.currentThread().getName()));
            return value + " from DB";
        }).thenApply(value -> {
            System.out.println(String.format("[Thread:%s]", Thread.currentThread().getName()));
            return value + " time:" + LocalDate.now();
        }).thenRun(() -> {
            System.out.println(String.format("[Thread:%s]", Thread.currentThread().getName()));
            System.out.println("over");
        })/*.exceptionally((e) -> {
            System.err.println(e);
        })*/;

        while (combineFuture.isDone()) ;

//        System.out.println(combineFuture.get());
        System.out.println();

        System.out.println(String.format("Starting....[Thread:%s]", Thread.currentThread().getName()));
    }
}
