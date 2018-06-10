package category.thread.ch07.threadExceptionMove.test;

import category.thread.ch07.threadExceptionMove.extthread.MyThread;
import category.thread.ch07.threadExceptionMove.extthreadgroup.MyThreadGroup;
import category.thread.ch07.threadExceptionMove.test.extUncaughtExceptionHandler.StateUncaughtExceptionHandler;

public class Run2 {

	public static void main(String[] args) {
		MyThreadGroup group = new MyThreadGroup("我的线程组");
		MyThread myThread = new MyThread(group, "我的线程");
		// 对象
//		 myThread
//		 .setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandler());
		// 类
		 MyThread
		 .setDefaultUncaughtExceptionHandler(new
				 StateUncaughtExceptionHandler());
		myThread.start();

	}
}
