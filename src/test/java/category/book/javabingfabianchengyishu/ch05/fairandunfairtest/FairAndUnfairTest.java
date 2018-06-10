package category.book.javabingfabianchengyishu.ch05.fairandunfairtest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/11 23:28
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class FairAndUnfairTest {


    private static ReentrantLock2 fairLock = new ReentrantLock2(true);
    private static ReentrantLock2 unfairLock = new ReentrantLock2(false);

    @Test
    public void fair() {
        System.out.println("公平锁开始................");
        long beginTime = System.currentTimeMillis();
        testLock(fairLock);
        System.out.println("公平锁结束...共耗时:" + (System.currentTimeMillis() - beginTime));
    }

    @Test
    public void unfair() {
        System.out.println("非公平锁开始................");
        long beginTime = System.currentTimeMillis();
        testLock(unfairLock);
        System.out.println("非公平锁结束...共耗时:" + (System.currentTimeMillis() - beginTime));
    }

    private void testLock(ReentrantLock2 lock) {
        // 启动7个Job（略）
        int num = 16;
        int count = 1000;
        final CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            final Thread thread = new Job(lock, countDownLatch, count);
            thread.setName("线程" + (i + 1));
            thread.setDaemon(true);
            thread.start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----------over-----------");
    }

    private static class Job extends Thread {
        private final static Integer COUNT = 2;
        private ReentrantLock2 lock;
        private CountDownLatch countDownLatch;
        private int count = COUNT;
        private int i = 0;

        public Job(ReentrantLock2 lock, CountDownLatch countDownLatch, int count) {
            this.lock = lock;
            this.countDownLatch = countDownLatch;
            this.count = count;
        }

        public void run() {
            while (this.i++ < count) {
                lock.lock();
//                System.out.println(System.currentTimeMillis()
//                        + "--获得锁--" + Thread.currentThread().getName()
//                        + "--第" + (i) + "次--" + lock.getQueuedThreads());
                // 连续2次打印当前的Thread和等待队列中的Thread（略）
                try {
                    Thread.currentThread().sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    System.out.println(System.currentTimeMillis()
//                            + "--释放锁--" + Thread.currentThread().getName()
//                            + "--第" + (i) + "次--" + lock.getQueuedThreads());
                    lock.unlock();
                }
                try {
                    Thread.currentThread().sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            countDownLatch.countDown();
        }

        @Override
        public String toString() {
            return this.getName();
        }
    }

    private static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        @Override
        public Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<Thread>(super.
                    getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }

    public static void main(String[] args) {
        new FairAndUnfairTest().fair();
        new FairAndUnfairTest().unfair();

    }
}
