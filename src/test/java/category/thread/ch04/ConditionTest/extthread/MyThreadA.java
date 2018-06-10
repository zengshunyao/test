package category.thread.ch04.ConditionTest.extthread;

import category.thread.ch04.ConditionTest.service.MyService;

public class MyThreadA extends Thread {

	private MyService myService;

	public MyThreadA(MyService myService) {
		super();
		this.myService = myService;
	}

	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i*=10000) {
			myService.set();
		}
		System.out.println("生产完了------------");
	}

}
