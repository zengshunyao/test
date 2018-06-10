package category.thread.ch03.joinTest2.test;

import category.thread.ch03.joinTest2.extthread.MyThread;

public class Test {

	public static void main(String[] args) {
		try {
			MyThread threadTest = new MyThread();
			threadTest.setName("son");
			threadTest.start();
			threadTest.join();

			System.out.println("我想当threadTest对象["+threadTest+"]执行完毕后我再执行，我做到了");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
