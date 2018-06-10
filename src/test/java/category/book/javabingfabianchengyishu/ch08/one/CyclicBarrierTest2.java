package category.book.javabingfabianchengyishu.ch08.one;

import java.util.concurrent.CyclicBarrier;

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
    static CyclicBarrier c = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                    System.out.println(Thread.currentThread().getName() + "==" + c.getNumberWaiting());
                } catch (Exception e) {
                }
                System.out.println(1);
            }
        }).start();

        try {
            System.out.println("---------");
            c.await();
        } catch (Exception e) {
        }
        System.out.println(2);
        c.reset();
        System.out.println();
    }

    static class A implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "==" + c.getNumberWaiting());
            System.out.println(3);
        }
    }
}
