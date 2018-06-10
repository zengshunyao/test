package category.thread.ch03.stack_4.service;

import category.thread.ch03.stack_4.entity.MyStack;

public class C {

	private MyStack myStack;

	public C(MyStack myStack) {
		super();
		this.myStack = myStack;
	}

	public void popService() {
		System.out.println("pop=" + myStack.pop());
	}

	public MyStack getMyStack() {
		return myStack;
	}
}
