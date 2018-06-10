package category.thread.ch04.ReadWriteLockBegin1.service;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Service {

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void read() {
		try {
			try {
				this.lock.readLock().lock();
				System.out.println("获得读锁" + Thread.currentThread().getName()
						+ " " + System.currentTimeMillis());
				Thread.sleep(10000);
				System.out.println("睡醒了" + Thread.currentThread().getName()
						+ " " + System.currentTimeMillis());
			} finally {
				this.lock.readLock().unlock();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
