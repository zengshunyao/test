package category.thread.ch04.ReadWriteLockBegin2.extthread;

import category.thread.ch04.ReadWriteLockBegin2.service.Service;

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
