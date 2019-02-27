package category.book.javabingfabianchengyishu.ch08.one.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/18 10:54
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class CyclicBarrierTest {
//    回环栅栏，通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
//    叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。
//    我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。

//    CyclicBarrier类位于java.util.concurrent包下，CyclicBarrier提供2个构造器：
//    public CyclicBarrier(int parties, Runnable barrierAction) {
//    }

//    public CyclicBarrier(int parties) {
//    }
//　　参数parties指让多少个线程或者任务等待至barrier状态；参数barrierAction为当这些线程都达到barrier状态时会执行的内容。
//   然后CyclicBarrier中最重要的方法就是await方法，它有2个重载版本：

//    public int await() throws InterruptedException, BrokenBarrierException { };
//    public int await(long timeout, TimeUnit unit)throws InterruptedException,BrokenBarrierException,TimeoutException { };
// 　　第一个版本比较常用，用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务；
//    第二个版本是让这些线程等待至一定的时间，如果还有线程没有到达barrier状态就直接让到达barrier的线程执行后续任务。


    static Random random = new Random();
    static int bound = 2000;
    static AtomicInteger mingci = new AtomicInteger(0);

    public static void main(String[] args) {
        int N = 4;//4个线程
        CyclicBarrier barrier = new CyclicBarrier(N);
        //开启4个线程写数据
        for (int i = 0; i < N; i++)
            new Writer(barrier).start();
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            int time = 1000 + random.nextInt(bound);
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...估计耗时" + time + "ms");
            try {
                Thread.sleep(time);      //以睡眠来模拟写入数据操作
                System.out.println("线程" + Thread.currentThread().getName()
                        + "写入数据完毕，第"
                        + mingci.incrementAndGet() + "名到达Barrier状态，正在等待其他线程写入完毕.....");

                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "搜到信号，所有线程写入完毕，继续处理其他任务...");
        }
    }
}
