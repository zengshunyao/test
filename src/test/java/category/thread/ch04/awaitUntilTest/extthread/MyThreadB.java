package category.thread.ch04.awaitUntilTest.extthread;

import category.thread.ch04.awaitUntilTest.service.Service;

public class MyThreadB extends Thread {

	private Service service;

	public MyThreadB(Service service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.notifyMethod();
	}

}
