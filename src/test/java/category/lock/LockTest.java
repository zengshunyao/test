package category.lock;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Lenovo on 2015/5/23.
 */
public class LockTest implements Runnable {
    private int value = 0;

    @Override
    public void run() {
        final Lock lock = new ReentrantLock();
        final Condition notFull = lock.newCondition();
        final Condition notEmpty = lock.newCondition();

//        lock.lock();
        try {
            try {
                notFull.await();
                AtomicBoolean atomicBoolean;
                File file;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 1000; i++) {
                System.out.println(++value + "<---->" + i);
                try {
                    Thread.sleep(3);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    notEmpty.signal();

                }
            }
        } finally {
//            lock.unlock();
        }
    }

    public static void main(String[] args) {

        Runnable t = new LockTest();
        for (int i = 0; i < 10; i++) {
            t.run();
        }
    }

}
