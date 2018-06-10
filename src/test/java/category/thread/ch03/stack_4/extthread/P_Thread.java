package category.thread.ch03.stack_4.extthread;

import category.thread.ch03.stack_4.service.P;

public class P_Thread extends Thread {

	private P p;

	public P_Thread(P p) {
		super();
		this.p = p;
	}

	@Override
	public void run() {
		while (p.getMyStack().isFlag()) {
			p.pushService();
		}
		System.out.println(Thread.currentThread().toString()+":生产者完了。。。");
	}

}
