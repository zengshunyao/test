package category.thread.ch04.ConditionTestManyToMany.extthread;

import category.thread.ch04.ConditionTestManyToMany.service.MyService;

public class MyThreadA extends Thread {

	private MyService myService;

	public MyThreadA(MyService myService) {
		super();
		this.myService = myService;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000/*Integer.MAX_VALUE*/; i++) {
			myService.set();
		}
		System.out.println("生产完了------------"+Thread.currentThread());
	}

}
