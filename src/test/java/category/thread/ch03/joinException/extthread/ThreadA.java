package category.thread.ch03.joinException.extthread;

public class ThreadA extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
//			String newString = new String();
//			Math.random();
			if((i&100000001)==1){
				System.out.println(i);
			}
		}
		System.out.println("我已经运行完成");
	}
}
