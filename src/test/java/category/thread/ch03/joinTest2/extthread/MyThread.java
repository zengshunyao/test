package category.thread.ch03.joinTest2.extthread;

public class MyThread extends Thread {

	@Override
	public void run() {
		try {
			int secondValue = (int) (Math.random() * 10000);
			System.out.println(secondValue);
			Thread.sleep(secondValue);
			System.out.println(Thread.currentThread()+"我已经执行完了");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
