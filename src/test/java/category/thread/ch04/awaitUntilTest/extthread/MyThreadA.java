package category.thread.ch04.awaitUntilTest.extthread;

import category.thread.ch04.awaitUntilTest.service.Service;

public class MyThreadA extends Thread {

	private Service service;

	public MyThreadA(Service service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.waitMethod();
	}

}
