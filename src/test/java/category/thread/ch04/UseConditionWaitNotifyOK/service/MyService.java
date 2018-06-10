package category.thread.ch04.UseConditionWaitNotifyOK.service;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {

	private Lock lock = new ReentrantLock();
	public Condition condition = lock.newCondition();

	public void await() {
		try {
			lock.lock();
			System.out.println(" await时间为：" + System.currentTimeMillis());
			condition.await();
			System.out.println(" 我执行完了："+System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void signal() {
		try {
			lock.lock();
			System.out.println("signal时间为：" + System.currentTimeMillis());
			condition.signal();
			System.out.println("唤醒了一个："+ System.currentTimeMillis());
		} finally {
			lock.unlock();
		}
	}

	public void signalAll() {
		try {
			lock.lock();
			System.out.println("signal时间为" + System.currentTimeMillis());
			condition.signalAll();
			System.out.println("唤醒了所有："+ System.currentTimeMillis());
		} finally {
			lock.unlock();
		}
	}
}
