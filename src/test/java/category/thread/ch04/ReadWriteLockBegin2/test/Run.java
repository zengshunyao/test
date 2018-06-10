package category.thread.ch04.ReadWriteLockBegin2.test;

import category.thread.ch04.ReadWriteLockBegin2.service.Service;
import category.thread.ch04.ReadWriteLockBegin2.extthread.ThreadA;
import category.thread.ch04.ReadWriteLockBegin2.extthread.ThreadB;

public class Run {

	public static void main(String[] args) {

		Service service = new Service();

		ThreadA a = new ThreadA(service);
		a.setName("A");

		ThreadB b = new ThreadB(service);
		b.setName("B");

		a.start();
		b.start();

	}

}
