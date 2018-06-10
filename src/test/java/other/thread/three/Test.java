package other.thread.three;

/**
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(描述该文件做什么)
 *
 * @project_name：${project_name}
 * @author ${user}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 * <p>
 * 我现在希望 A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3。这种需求下，
 * 显然 Thread.join() 已经不能满足了。我们需要更细粒度的锁来控制执行顺序。
 * <p>
 * 这里，我们可以利用 object.wait() 和 object.notify() 两个方法来实现。
 * <p>
 * 我现在希望 A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3。这种需求下，
 * 显然 Thread.join() 已经不能满足了。我们需要更细粒度的锁来控制执行顺序。
 * <p>
 * 这里，我们可以利用 object.wait() 和 object.notify() 两个方法来实现。
 * <p>
 * 我现在希望 A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3。这种需求下，
 * 显然 Thread.join() 已经不能满足了。我们需要更细粒度的锁来控制执行顺序。
 * <p>
 * 这里，我们可以利用 object.wait() 和 object.notify() 两个方法来实现。
 * <p>
 * 我现在希望 A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3。这种需求下，
 * 显然 Thread.join() 已经不能满足了。我们需要更细粒度的锁来控制执行顺序。
 * <p>
 * 这里，我们可以利用 object.wait() 和 object.notify() 两个方法来实现。
 * <p>
 * 我现在希望 A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3。这种需求下，
 * 显然 Thread.join() 已经不能满足了。我们需要更细粒度的锁来控制执行顺序。
 * <p>
 * 这里，我们可以利用 object.wait() 和 object.notify() 两个方法来实现。
 * <p>
 * 我现在希望 A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3。这种需求下，
 * 显然 Thread.join() 已经不能满足了。我们需要更细粒度的锁来控制执行顺序。
 * <p>
 * 这里，我们可以利用 object.wait() 和 object.notify() 两个方法来实现。
 */

/**
 * 我现在希望 A 在打印完 1 后，再让 B 打印 1, 2, 3，最后再回到 A 继续打印 2, 3。这种需求下，
 * 显然 Thread.join() 已经不能满足了。我们需要更细粒度的锁来控制执行顺序。
 * <p>
 * 这里，我们可以利用 object.wait() 和 object.notify() 两个方法来实现。
 */

/**
 * 那么，这个过程发生了什么呢？
 * 首先创建一个 A 和 B 共享的对象锁 lock = new Object();
 * 当 A 得到锁后，先打印 1，然后调用 lock.wait() 方法，交出锁的控制权，进入 wait 状态；
 * 对 B 而言，由于 A 最开始得到了锁，导致 B 无法执行；直到 A 调用 lock.wait() 释放控制权后，
 * B 才得到了锁；
 * B 在得到锁后打印 1， 2， 3；然后调用 lock.notify() 方法，唤醒正在 wait 的 A;
 * A 被唤醒后，继续打印剩下的 2，3。
 */
public class Test {

    public static void main(String[] args) {
        demo3();
    }

    private static void demo3() {
        final Object lock = new Object();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("INFO: A 等待锁");
                synchronized (lock) {
                    System.out.println("INFO: A 得到了锁 lock");
                    System.out.println("A 1");
                    try {
                        System.out.println("INFO: A 准备进入等待状态，放弃锁 lock 的控制权");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("INFO: 有人唤醒了 A, A 重新获得锁 lock");
                    System.out.println("A 2");
                    System.out.println("A 3");
                }
            }
        });
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("INFO: B 等待锁");
                synchronized (lock) {
                    System.out.println("INFO: B 得到了锁 lock");
                    System.out.println("B 1");
                    System.out.println("B 2");
                    System.out.println("B 3");
                    System.out.println("INFO: B 打印完毕，调用 notify 方法");
                    lock.notify();
                }
            }
        });
        A.start();
        B.start();
    }
}
