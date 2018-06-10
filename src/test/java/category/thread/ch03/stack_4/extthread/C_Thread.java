package category.thread.ch03.stack_4.extthread;

import category.thread.ch03.stack_4.service.C;

public class C_Thread extends Thread {

	private C r;

	public C_Thread(C r) {
		super();
		this.r = r;
	}

	@Override
	public void run() {
		while (r.getMyStack().isFlag()) {
			r.popService();
		}
		System.out.println(Thread.currentThread().toString()+"消费者完了。。。");
	}

}
