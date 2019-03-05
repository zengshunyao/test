package other.interview;

/**********************************************************************
 * &lt;p&gt;文件名：DemoLock.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2019/3/5 10:55
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class DemoLock {
    final static Object lock_1 = new Object();
    final static Object lock_2 = new Object();

    public static void main(String[] args) {

        Thread saojigong1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock_1) {
                    try {
                        System.out.println("1我拿到第1浩锁，等2号锁");
                        Thread.sleep(3000);
                        synchronized (lock_2) {
                            System.out.println("1我出不来的。。。。。");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread saojigong2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock_2) {
                    try {
                        System.out.println("2我拿到第2号锁，等1号锁");
                        Thread.sleep(3000);
                        synchronized (lock_1) {
                            System.out.println("2我出不来的。。。。。");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        saojigong1.start();
        saojigong2.start();

        System.out.println("坐等。。。。。。");

        //后边随便哪个都行
        //第一种
        try {
            saojigong1.join();
            //or
            saojigong2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //or
        //第二种
        System.out.println(System.in);
    }
}
