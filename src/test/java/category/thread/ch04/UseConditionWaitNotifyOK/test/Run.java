package category.thread.ch04.UseConditionWaitNotifyOK.test;


import category.thread.ch04.UseConditionWaitNotifyOK.extthread.ThreadA;
import category.thread.ch04.UseConditionWaitNotifyOK.service.MyService;

public class Run {

    public static void main(String[] args) throws InterruptedException {

        MyService service = new MyService();

        ThreadA a = new ThreadA(service);
        a.start();

        Thread.sleep(3000);

        //唤醒一个
        // service.signal();
        //唤醒所有
        service.signalAll();
    }
}
