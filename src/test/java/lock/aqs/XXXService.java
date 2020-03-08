package lock.aqs;

import lock.aqs.utils.UnsafeInstance;
import org.springframework.stereotype.Service;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**********************************************************************
 * &lt;p&gt;文件名：Service.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyaojava
 * @create 2020/3/8 18:42 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
@Service
public class XXXService {


    //自己实现
    //aqs  三板斧
    // 自旋，cas，LockSupport

    private volatile int state = 0;

    //
    private final static Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
    //记录state 内存中 相对对象的偏移位置
    private final static long offsetState;
    //记录 持有当前锁的线程
    private Thread lockHolder;
    //队列，记录抢锁失败的线程
    private final ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue();


    //同synchronized区别，JVM内置锁，不需要自己手动加锁和解锁
    //是一种显式锁，需要手动加锁和解锁，基于AQS同步实现的
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    static {
        Field stateField = null;
        try {
            stateField = XXXService.class.getField("state");
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
        offsetState = unsafe.objectFieldOffset(stateField);
    }

    public int getState() {
        return unsafe.getIntVolatile(this, offsetState);
    }

    /**
     * @param expect
     * @param update
     * @return
     */
    public boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, offsetState, expect, update);
    }

    public String miaosha() {

        //当前线程
        Thread current = Thread.currentThread();

        //T1,T2,T3,T4 进入抢锁
        //自旋抢锁
        for (; ; ) {
            int state = getState();
            if (state == 0) {
                if (compareAndSet(0, 1)) {
                    lockHolder = current;
                    break;
                }
            }
            //没抢到锁的线程阻塞
            //T2,T3,T4,
            //需要一个数据结构，保存被阻塞的线程，当持有锁的线程释放锁的时候去唤醒这些被阻塞的线程
            //FIFO队列去存储，避免像synchronized唤醒 停留阻塞在管程对象上的 所有 线程
            queue.add(current);
            LockSupport.park();
        }


        //核心业务
        boolean ret = this.jiankucun();

        //自旋，否者释放不了
        // 释放锁,
        for (; ; ) {
            int state = getState();
            if (state > 0 && lockHolder == current) {
                compareAndSet(1, 0);
                lockHolder = null;
                break;
            }
            //通知被阻塞的线程
            if (queue.size() > 0) {
                Thread nextThread = queue.poll();
                LockSupport.unpark(nextThread);
            }
        }


        if (ret) {
            return "秒杀成功";
        }
        return "秒杀失败";
    }


    private boolean jiankucun() {

        return false;
    }
}
