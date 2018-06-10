package category.thread.ch04.tryLock_param.test;

import category.thread.ch04.tryLock_param.service.MyService;

public class Run {

	public static void main(String[] args) throws InterruptedException {
		final MyService service = new MyService();

		Runnable runnableRef = new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()
						+ "调用waitMethod时间：" + System.currentTimeMillis());
				service.waitMethod();
			}
		};

		Thread threadA = new Thread(runnableRef);
		threadA.setName("A");
		threadA.start();

		Thread threadB = new Thread(runnableRef);
		threadB.setName("B");
		threadB.start();
	}
}