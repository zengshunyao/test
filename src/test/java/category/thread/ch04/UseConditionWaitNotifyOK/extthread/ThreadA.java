package category.thread.ch04.UseConditionWaitNotifyOK.extthread;


import category.thread.ch04.UseConditionWaitNotifyOK.service.MyService;

public class ThreadA extends Thread {

	private MyService service;

	public ThreadA(MyService service) {
		super();
		this.service = service;
	}

	@Override
	public void run() {
		service.await();
	}
}
