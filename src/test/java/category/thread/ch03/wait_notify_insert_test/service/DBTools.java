package category.thread.ch03.wait_notify_insert_test.service;

import java.util.concurrent.atomic.AtomicBoolean;

public class DBTools {

    volatile private AtomicBoolean prevIsA = new AtomicBoolean(false);

    synchronized public void backupA() {
        try {
            while (prevIsA.get()) {
                this.wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("★★★★★" + "---" + i);
            }
            this.prevIsA.set(true);
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void backupB() {
        try {
            while (!prevIsA.get()) {
                this.wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("☆☆☆☆☆" + "---" + i);
            }
            this.prevIsA.set(false);
            this.notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
