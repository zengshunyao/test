package other.thread.four;

import java.util.concurrent.CountDownLatch;

/**********************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(描述该文件做什么) 
 * @project_name：${project_name}
 * @author ${user}  
 * @date ${date} ${time} 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */

/**
 * 四个线程 A B C D，其中 D 要等到 A B C 全执行完毕后才执行，而且 A B C 是同步运行的
 * <p>
 * 最开始我们介绍了 thread.join()，可以让一个线程等另一个线程运行完毕后再继续执行，
 * 那我们可以在 D 线程里依次 join A B C，不过这也就使得 A B C 必须依次执行，而我们要的是这三者能同步运行。
 * <p>
 * 或者说，我们希望达到的目的是：A B C 三个线程同时运行，各自独立运行完后通知 D；对 D 而言，
 * 只要 A B C 都运行完了，D 再开始运行。针对这种情况，我们可以利用 CountdownLatch 来实现这类通信方式。它的基本用法是：
 * <p>
 * 创建一个计数器，设置初始值，CountdownLatch countDownLatch = new CountDownLatch(2);
 * 在 等待线程 里调用 countDownLatch.await() 方法，进入等待状态，直到计数值变成 0；
 * 在 其他线程 里，调用 countDownLatch.countDown() 方法，该方法会将计数值减小 1；
 * 当 其他线程 的 countDown() 方法把计数值变成 0 时，等待线程 里的 countDownLatch.await() 立即退出，继续执行下面的代码。
 */
public class Test {

    public static void main(String[] args) {
        runDAfterABC();
    }

    private static void runDAfterABC() {
        int worker = 3;
        final CountDownLatch countDownLatch = new CountDownLatch(worker);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("D is waiting for other three threads");
                try {
                    countDownLatch.await();
                    System.out.println("All done, D starts working");
                    System.out.println("D work over!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (char threadName = 'A'; threadName <= 'C'; threadName++) {
            final String tN = String.valueOf(threadName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(tN + " is working");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(tN + "finished");
                    countDownLatch.countDown();
                }
            }).start();
        }
    }
}
