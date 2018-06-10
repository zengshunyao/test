package category.thread.ch04.awaitUninterruptiblyTest_2.service;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Service {

	private ReentrantLock lock = new ReentrantLock();
	public Condition condition = lock.newCondition();

	public void testMethod() {
		try {
			this.lock.lock();
			System.out.println("wait begin");
//			this.condition.await();
			this.condition.awaitUninterruptibly();
			System.out.println("wait   end");
		/*} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("catch");
		*/} finally {
			this.lock.unlock();
		}
	}
}
