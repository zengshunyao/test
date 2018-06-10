package category.thread.ch04.ConditionTestMoreMethod.extthread;

import category.thread.ch04.ConditionTestMoreMethod.service.MyService;

public class ThreadAA extends Thread {

	private MyService service;

	public ThreadAA(MyService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.methodA();
	}
}
