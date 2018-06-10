package category.thread.ch04.lockMethodTest1.test1.test;

import category.thread.ch04.lockMethodTest1.test1.extthread.MyThreadA;
import category.thread.ch04.lockMethodTest1.test1.extthread.MyThreadB;
import category.thread.ch04.lockMethodTest1.test1.service.MyService;

public class Run {

	public static void main(String[] args) throws InterruptedException {
		MyService myService = new MyService();

//		myService.serviceMethod1();

		MyThreadA threadA = new MyThreadA(myService);
		MyThreadB threadB = new MyThreadB(myService);

		Thread.sleep(10);
		threadB.start();
		threadA.start();
//		MyThreadA[] threadA = new MyThreadA[10];
//		MyThreadB[] threadB = new MyThreadB[10];
//
//		for (int i = 0; i < 10; i++) {
//			threadA[i] = new MyThreadA(myService);
//			threadB[i] = new MyThreadB(myService);
//			threadA[i].start();
//			threadB[i].start();
//		}
	}
}
