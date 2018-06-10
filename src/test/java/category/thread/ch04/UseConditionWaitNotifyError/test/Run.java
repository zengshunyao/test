package category.thread.ch04.UseConditionWaitNotifyError.test;

import category.thread.ch04.UseConditionWaitNotifyError.service.MyService;
import category.thread.ch04.UseConditionWaitNotifyError.extthread.ThreadA;

public class Run {

	public static void main(String[] args) {

		MyService service = new MyService();

		ThreadA a = new ThreadA(service);
		a.start();

	}

}
