package category.thread.ch04.ConditionTestManyToMany.extthread;

import category.thread.ch04.ConditionTestManyToMany.service.MyService;

public class MyThreadB extends Thread {

	private MyService myService;

	public MyThreadB(MyService myService) {
		super();
		this.myService = myService;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000/*Integer.MAX_VALUE*/; i++) {
			myService.get();
		}
		System.out.println("消费完了------------"+Thread.currentThread());
	}

}
