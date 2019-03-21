package other.interview;

/**********************************************************************
 * &lt;p&gt;文件名：LockTest.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2019/3/5 10:41
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class LockTest {
    public static void main(String[] args) {
        System.out.println("begin...........");
        Object obj = new Object();
        Blackhole.enter(obj);
        System.out.println("end.....");
    }
}

class Blackhole {
    public static void enter(Object obj) {
        System.out.println("step1");
        magic(obj);
        System.out.println("step2");
        synchronized (obj) {
            System.out.println("step3");
        }
    }

    private static void magic(final Object obj) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    while (true) ;
                }
            }
        });
        t.start();
        try {
            t.join(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

