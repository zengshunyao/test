package category.thread.ch04.awaitUntilTest.test;

import category.thread.ch04.awaitUntilTest.extthread.MyThreadA;
import category.thread.ch04.awaitUntilTest.service.Service;

public class Run1 {

	public static void main(String[] args) throws InterruptedException {
		Service service = new Service();
		MyThreadA myThreadA = new MyThreadA(service);
		myThreadA.start();
	}

}
