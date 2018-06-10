package category.thread.ch04.ConditionTest.test;

import category.thread.ch04.ConditionTest.service.MyService;
import category.thread.ch04.ConditionTest.extthread.MyThreadA;
import category.thread.ch04.ConditionTest.extthread.MyThreadB;

public class Run {

	public static void main(String[] args) throws InterruptedException {
		MyService myService = new MyService();

		MyThreadA a = new MyThreadA(myService);
		a.start();

		MyThreadB b = new MyThreadB(myService);
		b.start();

	}
}
