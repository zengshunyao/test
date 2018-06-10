package category.thread.ch03.stack_4.service;

import category.thread.ch03.stack_4.entity.MyStack;

public class P {

	private MyStack myStack;

	public P(MyStack myStack) {
		super();
		this.myStack = myStack;
	}

	public void pushService() {
		myStack.push();
	}

	public MyStack getMyStack() {
		return myStack;
	}
}
