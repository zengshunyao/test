package category.designerpattern.singleton.staticinner;

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
public final class MyObject {

    //内部类方式
    private static class MyObjectHolder {
        static {
            System.out.println("static initing...");
        }

        //利用立即加载方式
        private static MyObject instance = new MyObject();
    }

    private MyObject() {
        super();
        System.out.println("construct initing...");
    }

    static MyObject getInstance() {
        //此版本为内部类方式
        return MyObjectHolder.instance;
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println(MyObject.getInstance().hashCode());
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.toString() + ":异常," + e.getLocalizedMessage());
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }
}

