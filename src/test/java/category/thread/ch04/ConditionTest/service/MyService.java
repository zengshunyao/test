package category.thread.ch04.ConditionTest.service;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private AtomicBoolean hasValue = new AtomicBoolean(false);

	public void set() {
		try {
			this.lock.lock();
			while (this.hasValue.get()) {
				this.condition.await();
			}
			System.out.println("打印★");
			this.hasValue.set(true);
			this.condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
	}

	public void get() {
		try {
			this.lock.lock();
			while (this.hasValue.get() == false) {
				this.condition.await();
			}
			System.out.println("打印☆");
			this.hasValue.set(false);
			this.condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.lock.unlock();
		}
	}
}