package category.thread.ch04.ReentrantLockTest.extthread;

import category.thread.ch04.ReentrantLockTest.service.MyService;

import java.util.Random;

public class MyThread extends Thread {

    private MyService service;

    public MyThread(MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.testMethod();
    }
}
