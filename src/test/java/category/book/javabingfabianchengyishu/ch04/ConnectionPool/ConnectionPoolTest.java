package category.book.javabingfabianchengyishu.ch04.ConnectionPool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/3 17:18
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class ConnectionPoolTest {
    final static ConnectionPool pool = new ConnectionPool(10);
    // 保证所有ConnectionRunner能够同时开始
    final static CountDownLatch start = new CountDownLatch(1);
    // main线程将会等待所有ConnectionRunner结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        // 线程数量，可以修改线程数量进行观察
        final int threadCount = 12;
        end = new CountDownLatch(threadCount);
        //重复循环次数
        final int count = 20;
        //成功获取次数
        AtomicInteger got = new AtomicInteger();
        //失败获取次数
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnetionRunner(count, got, notGot),
                    "ConnectionRunnerThread-" + i);
            thread.start();
        }
        //开始抢
        start.countDown();
        //所有线程抢完后
        end.await();
        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("got success connection: " + got);
        System.out.println("got fail connection " + notGot);
    }

    static class ConnetionRunner implements Runnable {
        int count;//重复循环次数
        AtomicInteger got;//成功获取次数
        AtomicInteger notGot;//失败获取次数

        public ConnetionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            while (count > 0) {
                try {
                    // 从线程池中获取连接，如果100ms内无法获取到，将会返回null
                    // 分别统计连接获取的数量got和未获取到的数量notGot
                    final Connection connection = pool.fetchConnection(100);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
