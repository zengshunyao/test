package category.thread.ch01.exthread;

public class MyThread1 extends Thread {

	@Override
	public void run() {
			while (true) {
				System.out.println("ok=" + System.currentTimeMillis());
				if (this.isInterrupted()) {
					System.out.println("停止了");
					return;
				}
				System.out.println("timer=" + System.currentTimeMillis());
			}
	}

}
