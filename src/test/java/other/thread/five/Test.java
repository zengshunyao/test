package other.thread.five;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

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
 * 三个运动员各自准备，等到三个人都准备好后，再一起跑
 * <p>
 * 上面是一个形象的比喻，针对 线程 A B C 各自开始准备，直到三者都准备完毕，然后再同时运行 。
 * 也就是要实现一种 线程之间互相等待 的效果，那应该怎么来实现呢？
 * <p>
 * 上面的 CountDownLatch 可以用来倒计数，但当计数完毕，只有一个线程的 await() 会得到响应，无法让多个线程同时触发。
 * <p>
 * 为了实现线程间互相等待这种需求，我们可以利用 CyclicBarrier 数据结构，它的基本用法是：
 * <p>
 * 先创建一个公共 CyclicBarrier 对象，设置 同时等待 的线程数，CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
 * 这些线程同时开始自己做准备，自身准备完毕后，需要等待别人准备完毕，这时调用 cyclicBarrier.await(); 即可开始等待别人；
 * 当指定的 同时等待 的线程数都调用了 cyclicBarrier.await();时，意味着这些线程都准备完毕好，然后这些线程才 同时继续执行。
 * <p>
 * 实现代码如下，设想有三个跑步运动员，各自准备好后等待其他人，全部准备好后才开始跑
 */
public class Test {

    public static void main(String[] args) {
        runABCWhenAllReady();
    }

    private static void runABCWhenAllReady() {
        int runner = 4;
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(runner);
        final Random random = new Random();
        for (char runnerName = 'A'; runnerName <= 'D'; runnerName++) {
            final String rN = String.valueOf(runnerName);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long prepareTime = random.nextInt(10000) + 100;
                    System.out.println(rN + " is preparing for time:" + prepareTime);
                    try {
                        Thread.sleep(prepareTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        System.out.println(rN + " is prepared, waiting for others");
                        cyclicBarrier.await(); // 当前运动员准备完毕，等待别人准备好
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(rN + " starts running"); // 所有运动员都准备好了，一起开始跑
                }
            }).start();
        }
    }
}
