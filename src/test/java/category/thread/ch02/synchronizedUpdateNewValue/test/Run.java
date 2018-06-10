package category.thread.ch02.synchronizedUpdateNewValue.test;

public class Run {


    public static void main(String[] args) {
        try {
            Service service = new Service();

            ThreadA a = new ThreadA(service);
            a.start();
            Thread.sleep(1000);

            ThreadB b = new ThreadB(service);
            b.start();

            System.out.println("已经发起停止的命令了！");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("已经发起停止的命令了2！");
    }
}

class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.runMethod();
    }
}

class ThreadB extends Thread {
    private Service service;

    public ThreadB(Service service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.stopMethod();
    }
}

class Service {
    private Boolean isContinueRun = Boolean.TRUE;

    public void runMethod() {
        String anyString = new String();
        int counter = 0;
        while (isContinueRun) {
            //疑问：
            //此处任意打开一个，就能实现b线程修改isContinueRun后，a能力立即读取到修改后的内容
            //但是不打开就永远死循环，感觉读取不到b线程修改isContinueRun后新的值
//1.
//            synchronized (anyString) {
//            这里写的很多业务代码
//            }
//            System.out.println("in run:" + (++counter));
        }
        System.out.println("停下来了！");
    }

    public void stopMethod() {
        this.isContinueRun = Boolean.FALSE;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("over...");
    }
}
