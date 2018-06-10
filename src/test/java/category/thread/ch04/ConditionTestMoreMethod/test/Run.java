package category.thread.ch04.ConditionTestMoreMethod.test;

import category.thread.ch04.ConditionTestMoreMethod.extthread.ThreadA;
import category.thread.ch04.ConditionTestMoreMethod.extthread.ThreadAA;
import category.thread.ch04.ConditionTestMoreMethod.extthread.ThreadB;
import category.thread.ch04.ConditionTestMoreMethod.extthread.ThreadBB;
import category.thread.ch04.ConditionTestMoreMethod.service.MyService;

public class Run {

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();

        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadAA aa = new ThreadAA(service);
        aa.setName("AA");
        aa.start();

        Thread.sleep(100);

        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();

        ThreadBB bb = new ThreadBB(service);
        bb.setName("BB");
        bb.start();

    }

}
