package category.thread.ch04.awaitUninterruptiblyTest_1.service;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void testMethod() {
		try {
			this.lock.lock();
			System.out.println("wait begin");
			this.condition.await();
			System.out.println("wait   end");
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("catch");
		} finally {
			this.lock.unlock();
		}
	}
}
