package category.thread.ch01.currentThreadExt.mythread;

public class Run {

    public static void main(String[] args) {
        CountOperate c = new CountOperate();
//		c.setName("D");
        Thread t1 = new Thread(c);
        t1.setName("A");
        t1.start();

//		c.start();
//		System.out.println("currentThread1:"+Thread.currentThread());
//		System.out.println("currentThread2:"+c);
    }
}