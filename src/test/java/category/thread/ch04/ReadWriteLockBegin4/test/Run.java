package category.thread.ch04.ReadWriteLockBegin4.test;

import category.thread.ch04.ReadWriteLockBegin4.extthread.ThreadA;
import category.thread.ch04.ReadWriteLockBegin4.extthread.ThreadB;
import category.thread.ch04.ReadWriteLockBegin4.service.Service;

/**
 * 读写互斥
 */
public class Run {

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        ThreadB b = new ThreadB(service);
        b.setName("write");
        b.start();

        Thread.sleep(1000);

        ThreadA a = new ThreadA(service);
        a.setName("read");
        a.start();
    }
}
