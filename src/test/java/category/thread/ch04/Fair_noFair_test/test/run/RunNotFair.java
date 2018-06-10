package category.thread.ch04.Fair_noFair_test.test.run;

import category.thread.ch04.Fair_noFair_test.service.Service;

public class RunNotFair {

    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service(false);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("★线程" + Thread.currentThread().getName()
                        + "运行了");
                service.serviceMethod();
            }
        };

        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(runnable);
            threadArray[i].setName("线程" + (i + 1));
        }
        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }

    }
}
