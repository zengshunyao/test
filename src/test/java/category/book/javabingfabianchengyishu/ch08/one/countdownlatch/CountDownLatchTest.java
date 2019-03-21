package category.book.javabingfabianchengyishu.ch08.one.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/18 10:49
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class CountDownLatchTest {

    //然后下面这3个方法是CountDownLatch类中最重要的方法：
    //
    //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
    //public void await() throws InterruptedException { };

    //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
    //public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };

    //将count值减1
    //public void countDown() { };
    final static Integer NUM = 4;

    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(NUM);

        new Thread() {
            private String name = "1";

            public void run() {
                try {
                    System.out.println("子线程" + name + "------" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程" + name + "------" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            private String name = "2";

            public void run() {
                try {
                    System.out.println("子线程" + name + "------" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程" + name + "------" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            private String name = "3";

            public void run() {
                try {
                    System.out.println("子线程" + name + "------" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(5000);
                    System.out.println("子线程" + name + "------" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                    System.out.println("子线程" + name + "------" + Thread.currentThread().getName() + "countDown");
                    latch.await();
                    System.out.println("子线程" + name + "------" + "over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            private String name = "4";

            public void run() {
                try {
                    System.out.println("子线程" + name + "------" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(5000);
                    System.out.println("子线程" + name + "------" + Thread.currentThread().getName() + "执行完毕");
                    latch.countDown();
                    System.out.println("子线程" + name + "------" + Thread.currentThread().getName() + "countDown");
                    latch.await();
                    System.out.println("子线程" + name + "------" + "over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        try {
            System.out.println("等待" + NUM + "个子线程执行完毕...");
            latch.await();
            System.out.println(NUM + "个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
