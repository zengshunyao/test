package category.thread.ch04.tryLockTest.service;

import java.util.concurrent.locks.ReentrantLock;

public class MyService {

    public ReentrantLock lock = new ReentrantLock();

    public void waitMethod() {
        if (lock.tryLock()) {
            System.out.println(Thread.currentThread().getName() + "获得锁");
            System.out.println(Thread.currentThread().getName() + "我拿到锁了......");
            System.out.println(Thread.currentThread().getName() + "前===="+lock.getHoldCount());
//			System.out.println("我拿到锁了......");
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "后===="+lock.getHoldCount());
        } else {
            System.out.println(Thread.currentThread().getName() + "没有获得锁");
        }
    }
}
