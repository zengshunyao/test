package category.thread.ch04.ReadWriteLockBegin4.extthread;

import category.thread.ch04.ReadWriteLockBegin4.service.Service;

public class ThreadB extends Thread {

	private Service service;

	public ThreadB(Service service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.write();
	}
}
