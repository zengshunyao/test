package category.thread.ch04.Fair_noFair_test.service;

import java.util.concurrent.locks.ReentrantLock;

public class Service {

	private ReentrantLock lock;

	public Service(boolean isFair) {
		super();
		this.lock = new ReentrantLock(isFair);
	}

	public void serviceMethod() {
		try {
			this.lock.lock();
			System.out.println("ThreadName=" + Thread.currentThread().getName()
					+ "获得锁定");
		} finally {
			this.lock.unlock();
		}
	}

}
