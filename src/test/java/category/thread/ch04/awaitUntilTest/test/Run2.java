package category.thread.ch04.awaitUntilTest.test;

import category.thread.ch04.awaitUntilTest.service.Service;
import category.thread.ch04.awaitUntilTest.extthread.MyThreadA;
import category.thread.ch04.awaitUntilTest.extthread.MyThreadB;

public class Run2 {

	public static void main(String[] args) throws InterruptedException {
		Service service = new Service();
		MyThreadA myThreadA = new MyThreadA(service);
		myThreadA.start();

		Thread.sleep(2000);

		MyThreadB myThreadB = new MyThreadB(service);
		myThreadB.start();
	}

}
