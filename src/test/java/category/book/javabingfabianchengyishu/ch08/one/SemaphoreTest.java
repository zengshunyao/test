package category.book.javabingfabianchengyishu.ch08.one;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
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
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool = Executors
            .newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(10);
    private static AtomicInteger a = new AtomicInteger(0);

    public static void main(String[] args) {
//        Executors.newScheduledThreadPool(20);
        //
        Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println(a.incrementAndGet() + "-"
                                + Thread.currentThread().getName()
                                + "--save data--" + s.availablePermits());
                        s.release();
//                        s.drainPermits();
                        s.hasQueuedThreads();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPool.shutdown();
    }
}
