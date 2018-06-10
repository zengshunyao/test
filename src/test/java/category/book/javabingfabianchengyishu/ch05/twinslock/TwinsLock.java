package category.book.javabingfabianchengyishu.ch05.twinslock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(双胞胎锁)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/11 16:53
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class TwinsLock implements Lock, java.io.Serializable {

    private final Sync sync = new Sync(2);

    @Override
    public void lock() {
        int count = sync.tryAcquireShared(1);
        System.out.println(Thread.currentThread().getName() + "占用--------" + count);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        public Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero.");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount) {
//            return super.tryAcquireShared(arg);
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount >= 0 && compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
//            return super.tryReleaseShared(arg);
            for (; ; ) {
                int current = getState();
                if (current > 2 || current < 0) {
                    throw new IllegalStateException("错误");
                }
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    System.out.println(Thread.currentThread().getName() + "释放--------" + newCount);
                    return true;
                }
            }
        }
    }


}
