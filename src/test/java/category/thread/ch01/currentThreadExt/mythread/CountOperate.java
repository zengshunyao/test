package category.thread.ch01.currentThreadExt.mythread;

public class CountOperate extends Thread {

	public CountOperate() {
		System.out.println("CountOperate---begin");
		System.out.println("Thread.currentThread().getName()="
				+ Thread.currentThread().getName());
		System.out.println("this.getName()=" + this.getName());
		System.out.println(Thread.currentThread()==this);
		System.out.println("CountOperate---end");
	}

	@Override
	public void run() {
		try {
			System.out.println("this.isAlive:"+this.isAlive());
			Thread.sleep(10);
			System.out.println("Thread.currentThread().isAlive:"+Thread.currentThread().isAlive());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("run---begin");
		System.out.println("Thread.currentThread().getName()="
				+ Thread.currentThread().getName());
		System.out.println("this.getName()=" + this.getName());
		System.out.println(Thread.currentThread()==this);
		System.out.println("run---end");
	}
}
