package category.thread.ch04.lockMethodTest1.test1.extthread;

import category.thread.ch04.lockMethodTest1.test1.service.MyService;

public class MyThreadA extends Thread {

	private MyService myService;

	public MyThreadA(MyService myService) {
		super();
		this.myService = myService;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread()+"---"+System.currentTimeMillis());
		myService.serviceMethod1();
		System.out.println("生产完了------------"+Thread.currentThread());
	}

}
