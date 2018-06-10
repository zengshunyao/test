package category.thread.ch03.InheritableThreadLocal1.extthread;

import category.thread.ch03.InheritableThreadLocal1.tools.Tools;

public class ThreadA extends Thread {

	@Override
	public void run() {
		try {
			for (int i = 0; i < 2; i++) {
				System.out.println("在ThreadA线程中取值=" + Tools.tl.get());
				Thread.sleep(10);
			}
			Tools.tl.set("dfdfdf");
			System.out.println("------------------修改后--------------");
			for (int i = 0; i < 2; i++) {
				System.out.println("在ThreadA线程中取值=" + Tools.tl.get());
				Thread.sleep(10);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
