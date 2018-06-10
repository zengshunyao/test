package category.thread.ch04.ReadWriteLockBegin2.service;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Service {

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void write() {
		try {
			try {
				lock.writeLock().lock();
				System.out.println("获得写锁" + Thread.currentThread().getName()
						+ " " + System.currentTimeMillis());
				Thread.sleep(10000);
				System.out.println("我睡醒了" + Thread.currentThread().getName()
						+ " " + System.currentTimeMillis());
			} finally {
				lock.writeLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
