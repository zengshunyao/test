package category.thread.ch07.threadExceptionMove.test;

import category.thread.ch07.threadExceptionMove.extthread.MyThread;
import category.thread.ch07.threadExceptionMove.test.extUncaughtExceptionHandler.StateUncaughtExceptionHandler;

public class Run1 {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        // 对象
//         myThread
//         .setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandler());
        // 类
        MyThread.setDefaultUncaughtExceptionHandler(new StateUncaughtExceptionHandler());
        myThread.start();
    }
}