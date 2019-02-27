package category.book.javabingfabianchengyishu.ch08.one.semaphore;

import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：(流量控制)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/18 14:12
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class SemaphoreTest extends Thread {

    /**
     * Semaphore翻译成字面意思为 信号量，Semaphore可以控同时访问的线程个数，通过 acquire() 获取一个许可，如果没有就等待，而 release() 释放一个许可。
     *
     * Semaphore类位于java.util.concurrent包下，它提供了2个构造器：
     *
     * //参数permits表示许可数目，即同时可以允许多少线程进行访问
     * public Semaphore(int permits) {
     *     sync = new NonfairSync(permits);
     * }
     * //这个多了一个参数fair表示是否是公平的，即等待时间越久的越先获取许可
     * public Semaphore(int permits, boolean fair) {
     *     sync = (fair)? new FairSync(permits) : new NonfairSync(permits);
     * }
     *
     *
     * 下面说一下Semaphore类中比较重要的几个方法，首先是acquire()、release()方法：
     *
     * //获取一个许可
     * public void acquire() throws InterruptedException {  }
     * //获取permits个许可
     * public void acquire(int permits) throws InterruptedException { }
     * //释放一个许可
     * public void release() { }
     * //释放permits个许可
     * public void release(int permits) { }
     * 　　
     *    acquire()用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许可。
     * 　　release()用来释放许可。注意，在释放许可之前，必须先获获得许可。
     * 　　这4个方法都会被阻塞，如果想立即得到执行结果，可以使用下面几个方法：
     *
     * //尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
     * public boolean tryAcquire() { };
     * //尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
     * public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };
     * //尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
     * public boolean tryAcquire(int permits) { };
     * //尝试获取permits个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
     * public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { };
     * //另外还可以通过availablePermits()方法得到可用的许可数目。
     */

    /**
     * 控制某资源同时被访问的个数的类 控制同一时间最后只能有50个访问
     */
    static Semaphore semaphore = new Semaphore(50);
    static int timeout = 500;

    static AtomicInteger jishuqi = new AtomicInteger(0);

    @Override
    public void run() {
        try {
            int num = jishuqi.incrementAndGet();
            System.out.println("线程序号" + num + ".获取前,当前还有" + semaphore.availablePermits() + "空位");
            Object conn = getConnection();
            System.out.println("线程序号" + num + ".获取成功,当前还有" + semaphore.availablePermits() + "空位");
            System.out.println("获得一个连接(" + conn + "),当前还有" + semaphore.availablePermits() + "空位");
            Thread.sleep(300);
            System.out.println("线程序号" + num + ".释放前,当前还有" + semaphore.availablePermits() + "空位");
            releaseConnection(conn);
            System.out.println("线程序号" + num + ".释放成功,当前还有" + semaphore.availablePermits() + "空位");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("获取异常...");
        }
    }

    public void releaseConnection(Object connec) {
        /* 释放许可 */
        semaphore.release();
//        System.out.println("释放一个连接" + connec);
    }

    public Object getConnection() {
        try {/* 获取许可 */
            boolean getAccquire = semaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS);
            if (getAccquire) {
                return UUID.randomUUID().toString().hashCode();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("timeout");
    }

    public static void main(String[] args) {
        int i = 0;
        while (i < 500) {
            i++;
            new SemaphoreTest().start();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
