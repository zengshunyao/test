package category.thread.ch04.ReadWriteLockBegin1.extthread;

import category.thread.ch04.ReadWriteLockBegin1.service.Service;

public class ThreadA extends Thread {

	private Service service;

	public ThreadA(Service service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.read();
	}
}
