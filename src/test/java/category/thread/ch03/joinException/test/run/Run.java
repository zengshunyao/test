package category.thread.ch03.joinException.test.run;

import category.thread.ch03.joinException.extthread.ThreadB;
import category.thread.ch03.joinException.extthread.ThreadC;

public class Run {

	public static void main(String[] args) {

		try {
			ThreadB b = new ThreadB();
			b.start();

			Thread.sleep(50);

			ThreadC c = new ThreadC(b);
			c.start();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
