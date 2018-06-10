package category.thread.ch03.InheritableThreadLocal1.test;

import category.thread.ch03.InheritableThreadLocal1.extthread.ThreadA;
import category.thread.ch03.InheritableThreadLocal1.tools.Tools;

public class Run {

	public static void main(String[] args) {
		try {
			for (int i = 0; i < 2; i++) {
				System.out.println("   在Main线程中取值=" + Tools.tl.get());
				Thread.sleep(10);
			}
//			Tools.tl.set("parent");
			Thread.sleep(50);
			ThreadA a = new ThreadA();
			a.start();
			a.join();
			System.out.println("  2在Main线程中取值=" + Tools.tl.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}