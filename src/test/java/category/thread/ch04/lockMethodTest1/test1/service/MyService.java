package category.thread.ch04.lockMethodTest1.test1.service;

import java.util.concurrent.locks.ReentrantLock;

public class MyService {

    private ReentrantLock lock = new ReentrantLock();

    public void serviceMethod1() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread()+"--serviceMethod1 getHoldCount="
                    + lock.getHoldCount());//查询当前线程保持锁定的个数，也就是调用lock()的次数
            serviceMethod2();
            System.out.println(Thread.currentThread()+"--Method1完了....");
        } finally {
            System.out.println(Thread.currentThread()+"--finally serviceMethod1 getHoldCount="
                    + lock.getHoldCount());
            lock.unlock();
            System.out.println(Thread.currentThread()+"--1释放锁了。。。");
        }
    }

    public void serviceMethod2() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread()+"--serviceMethod2 getHoldCount="
                    + lock.getHoldCount());
            System.out.println(Thread.currentThread()+"--Method2完了....");
        } finally {
            System.out.println(Thread.currentThread()+"--finally serviceMethod2 getHoldCount="
                    + lock.getHoldCount());
            lock.unlock();
            System.out.println(Thread.currentThread()+"--2释放锁了。。。");
        }
    }

}
