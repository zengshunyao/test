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
 * @date 2018/4/18 11:08
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class CyclicBarrierTest2 {

    static Random random = new Random();
    static int bound = 2000;
    static AtomicInteger mingci = new AtomicInteger(0);

    public static void main(String[] args) {
        int N = 4;//4个线程
        CyclicBarrier barrier = new CyclicBarrier(N, () -> {
            System.out.println("他们都调用了await，" + "最后一名线程" + Thread.currentThread().getName() + "收拾残局了......");
        });
        //开启4个线程写数据
        for (int i = 0; i < N; i++)
            new CyclicBarrierTest.Writer(barrier).start();
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
