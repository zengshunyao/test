package category.thread.ch04.lockMethodTest2.test1;

public class Run {

	public static void main(String[] args) throws InterruptedException {
		final Service service = new Service();

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				service.waitMethod();
			}
		};

		Thread threadA = new Thread(runnable);
		threadA.start();

		Thread.sleep(500);

		Thread threadB = new Thread(runnable);
		threadB.start();

		Thread.sleep(500);
		System.out.println("线程A是否在等待锁："+service.lock.hasQueuedThread(threadA));
		System.out.println("线程B是否在等待锁："+service.lock.hasQueuedThread(threadB));
		System.out.println("是否存在等待锁的线程："+service.lock.hasQueuedThreads());
	}
}
