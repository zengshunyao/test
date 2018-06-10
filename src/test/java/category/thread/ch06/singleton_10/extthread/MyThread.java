package category.thread.ch06.singleton_10.extthread;

import category.thread.ch06.singleton_10.test.MyObject;

public class MyThread extends Thread {

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(MyObject.getConnection()
					.hashCode());
		}
	}
}
