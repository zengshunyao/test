package category.thread.ch04.ConditionTest.extthread;

import category.thread.ch04.ConditionTest.service.MyService;

public class MyThreadB extends Thread {

	private MyService myService;

	public MyThreadB(MyService myService) {
		super();
		this.myService = myService;
	}

	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i*=10000) {
			myService.get();
		}
		System.out.println("消费完了------------");
	}

}
