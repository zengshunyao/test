package category.thread.ch04.ConditionTestMoreMethod.extthread;

import category.thread.ch04.ConditionTestMoreMethod.service.MyService;

public class ThreadBB extends Thread {

    private MyService service;

    public ThreadBB(MyService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.methodB();
    }
}
