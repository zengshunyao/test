package category.jdk.java15.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**********************************************************************
 * &lt;p&gt;文件名：java15.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2019/4/10 19:43 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(String.format("hell world[Thread:%s]", Thread.currentThread().getName()));
            }
        });

        executor.shutdown();

        //CountDownLatch
        //CyclicBarrier
        //ConcurrentHashMap
    }
}
