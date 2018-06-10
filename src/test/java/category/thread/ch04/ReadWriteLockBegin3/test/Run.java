package category.thread.ch04.ReadWriteLockBegin3.test;

import category.thread.ch04.ReadWriteLockBegin3.service.Service;
import category.thread.ch04.ReadWriteLockBegin3.extthread.ThreadA;
import category.thread.ch04.ReadWriteLockBegin3.extthread.ThreadB;

/**
 * 读写互斥
 */
public class Run {

	public static void main(String[] args) throws InterruptedException {

		Service service = new Service();

		ThreadA a = new ThreadA(service);
		a.setName("read");
		a.start();

		Thread.sleep(1000);

		ThreadB b = new ThreadB(service);
		b.setName("write");
		b.start();
	}
}
