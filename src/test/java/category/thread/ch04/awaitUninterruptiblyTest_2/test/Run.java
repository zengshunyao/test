package category.thread.ch04.awaitUninterruptiblyTest_2.test;

import category.thread.ch04.awaitUninterruptiblyTest_2.extthread.MyThread;
import category.thread.ch04.awaitUninterruptiblyTest_2.service.Service;

public class Run {

	public static void main(String[] args) {
		try {
			Service service = new Service();
			MyThread myThread = new MyThread(service);
			myThread.start();
			Thread.sleep(30);
			myThread.interrupt();
			//
			Thread.sleep(300);
			service.condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("main catch");
		}
	}
}
