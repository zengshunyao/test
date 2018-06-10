package category.book.javabingfabianchengyishu.ch05.twinslock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/11 17:05
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class TwinsLockTest {
    @Test
    public void test() {
        long beginTime = System.currentTimeMillis();
        final int threadCount = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        final Lock lock = new TwinsLock();

        class Worker extends Thread {
            public void run() {
                do {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
//                        System.out.println(System.currentTimeMillis() + "==我是" + Thread.currentThread().getName() + "-----------" + this.num);
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                } while (--num > 0);
                countDownLatch.countDown();
//                System.out.println(System.currentTimeMillis() + "=玩完=" + Thread.currentThread().getName() + ",当前还有：" + countDownLatch.getCount() + "个线程未释放");
            }

            private CountDownLatch countDownLatch;

            private int num = 100;

            public Worker(CountDownLatch countDownLatch) {
                this.countDownLatch = countDownLatch;
            }
        }
        // 启动10个线程
        for (int i = 0; i < threadCount; i++) {
            Worker w = new Worker(countDownLatch);
            w.setDaemon(true);
            w.setName("线程" + (i + 1));
            w.start();
        }
//        SleepUtils.second(5 * 1000L);
        // 每隔1秒换行
//        for (int i = 0; i < threadCount; i++) {
//            SleepUtils.second(1);
//            System.out.println(System.currentTimeMillis() + "==" + i + "当前线程数量：" + Thread.currentThread().getThreadGroup().activeCount());
//        }
        try {
//            System.out.println(System.currentTimeMillis() + "---------begin OVER---------");
//            System.out.println(System.currentTimeMillis() + "==A当前线程数量：" + Thread.currentThread().getThreadGroup().activeCount());
//            countDownLatch.await(999999, TimeUnit.MILLISECONDS);
            countDownLatch.await();
            System.out.println(System.currentTimeMillis() + "---------OVER---------" + (System.currentTimeMillis() - beginTime) + "ms.");
//            System.out.println(System.currentTimeMillis() + "==B当前线程数量：" + Thread.currentThread().getThreadGroup().activeCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SleepUtils {
    public static void second(long i) {
        try {
            Thread.currentThread().sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
