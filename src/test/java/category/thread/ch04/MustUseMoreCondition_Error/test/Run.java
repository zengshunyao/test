package category.thread.ch04.MustUseMoreCondition_Error.test;

import category.thread.ch04.MustUseMoreCondition_Error.service.MyService;
import category.thread.ch04.MustUseMoreCondition_Error.extthread.ThreadA;
import category.thread.ch04.MustUseMoreCondition_Error.extthread.ThreadB;

public class Run {

	public static void main(String[] args) throws InterruptedException {

		MyService service = new MyService();

		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();

		ThreadB b = new ThreadB(service);
		b.setName("B");
		b.start();

		Thread.sleep(3000);

		service.signalAll();

	}

}
