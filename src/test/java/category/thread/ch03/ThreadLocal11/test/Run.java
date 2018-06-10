package category.thread.ch03.ThreadLocal11.test;

public class Run {
	public static ThreadLocal tl = new ThreadLocal();

	public static void main(String[] args) {
		System.out.println(tl.get());
		if (tl.get() == null) {
			System.out.println("从未放过值");
			tl.set("我的值");
		}
		System.out.println(tl.get());
		System.out.println(tl.get());
		tl.set("dfdf");
		System.out.println(tl.get());
//		System.out.println(tl.getMap(Thread.currentThread()));
	}

}
