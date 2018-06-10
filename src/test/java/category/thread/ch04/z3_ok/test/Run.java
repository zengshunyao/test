package category.thread.ch04.z3_ok.test;

import category.thread.ch04.z3_ok.service.MyService;
import category.thread.ch04.z3_ok.extthread.MyThreadA;

public class Run {

	public static void main(String[] args) {
		MyService myService = new MyService();
		MyThreadA a = new MyThreadA(myService);
		a.start();
	}
}
