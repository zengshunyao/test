package category.thread.ch04.ReadWriteLockBegin3.service;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Service {

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void read() {
		try {
			try {
				lock.readLock().lock();
				System.out.println("获得读锁" + Thread.currentThread().getName()
						+ " " + System.currentTimeMillis());
				Thread.sleep(10000);
				System.out.println("睡醒了" + Thread.currentThread().getName()
						+ " " + System.currentTimeMillis());
			} finally {
				lock.readLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void write() {
		try {
			try {
				lock.writeLock().lock();
				System.out.println("获得写锁" + Thread.currentThread().getName()
						+ " " + System.currentTimeMillis());
				Thread.sleep(10000);
				System.out.println("睡醒了" + Thread.currentThread().getName()
						+ " " + System.currentTimeMillis());
			} finally {
				lock.writeLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
