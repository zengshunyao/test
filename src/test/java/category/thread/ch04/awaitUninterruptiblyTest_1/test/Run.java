package category.thread.ch04.awaitUninterruptiblyTest_1.test;

import category.thread.ch04.awaitUninterruptiblyTest_1.service.Service;
import category.thread.ch04.awaitUninterruptiblyTest_1.extthread.MyThread;

public class Run {

	public static void main(String[] args) {
		try {
			Service service = new Service();
			MyThread myThread = new MyThread(service);
			myThread.start();
			Thread.sleep(3000);
			myThread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("main catch");
		}
	}

}
