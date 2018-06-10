package category.thread.ch04.ConditionTestMoreMethod.extthread;

import category.thread.ch04.ConditionTestMoreMethod.service.MyService;

public class ThreadB extends Thread {

	private MyService service;

	public ThreadB(MyService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.methodB();
	}
}
