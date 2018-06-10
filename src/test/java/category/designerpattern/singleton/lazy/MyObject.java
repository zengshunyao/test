package category.designerpattern.singleton.lazy;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(描述该文件做什么)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class MyObject {

    //延迟加载方式==懒汉模式
    private static volatile MyObject instance = null;

    private MyObject() {
        super();
    }

    public static MyObject getInstance() {
        //此版本为延迟加载
        //此版本代码的缺点是不能有其他实例变量
        //因为getInstance方法没有同步
        //所以可能出现非线程安全问题
        if (instance == null) {
            synchronized (MyObject.class) {
                if (instance == null) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new MyObject();
                }
            }
        }
        return instance;
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println(MyObject.getInstance().hashCode());
    }
}

class Run {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("异常");
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
