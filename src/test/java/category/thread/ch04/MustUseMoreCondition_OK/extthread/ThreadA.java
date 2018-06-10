package category.thread.ch04.MustUseMoreCondition_OK.extthread;

import category.thread.ch04.MustUseMoreCondition_OK.service.MyService;

public class ThreadA extends Thread {

	private MyService service;

	public ThreadA(MyService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.awaitA();
	}
}
