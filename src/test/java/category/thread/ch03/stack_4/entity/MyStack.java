package category.thread.ch03.stack_4.entity;

import java.util.ArrayList;
import java.util.List;

public class MyStack {
	private List list = new ArrayList();

	private boolean flag=true;

	synchronized public boolean isFlag() {
		return flag;
	}

	synchronized public void setFlag(boolean flag) {
		this.flag = flag;
	}

	synchronized public void push() {
		try {
			while (list.size() == 1) {
				if(!this.isFlag()){
//					Thread.currentThread().interrupt();
					break;
				}
				System.out.println("push操作中的："
						+ Thread.currentThread().getName() + " 线程呈wait状态");
				this.wait();
			}
			list.add("anyString=" + Math.random());
			this.notifyAll();
			System.out.println("push=" + list.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	synchronized public String pop() {
		String returnValue = "";
		try {
			while (list.size() == 0) {
				if(!this.isFlag()){
//					Thread.currentThread().interrupt();
//					return ;
				}
				System.out.println("pop操作中的："
						+ Thread.currentThread().getName() + " 线程呈wait状态");
				this.wait();
			}
			returnValue = "" + list.get(0);
			list.remove(0);
			this.notifyAll();
			System.out.println("pop=" + list.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
}
