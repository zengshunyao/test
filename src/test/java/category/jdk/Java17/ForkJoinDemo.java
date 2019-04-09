package category.jdk.Java17;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**********************************************************************
 * &lt;p&gt;文件名：ForkJoinDemo.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2019/4/9 23:21 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class ForkJoinDemo {

    public static void main(String[] args) {
        System.out.println("当前CPU处理器数量：" + ForkJoinPool.commonPool().getParallelism());
        System.out.println("当前CPU处理器数量：" + Runtime.getRuntime().availableProcessors());

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new RecursiveAction() {
            @Override
            protected void compute() {
                System.out.println(String.format("Thread:%s", Thread.currentThread().getName()));
            }
        });

        forkJoinPool.shutdown();

    }
}
