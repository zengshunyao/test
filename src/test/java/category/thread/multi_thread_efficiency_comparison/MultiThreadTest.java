package category.thread.multi_thread_efficiency_comparison;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(线程安全的+1操作实现种类)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/6/3 22:28
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */

public class MultiThreadTest extends Thread {
/*
    //整体表现最好,短时间的低并发下比AtomicInteger性能差一点，高并发下性能最高；
    private static LongAdder longAdder = new LongAdder();

    //短时间低并发下，效率比synchronized高，甚至比LongAdder还高出一点，但是高并发下，
    // 性能还不如synchronized；不同情况下性能表现很不稳定；可见atomic只适合锁争用不激烈的场景
    private static AtomicInteger atomInteger = new AtomicInteger(0);

    //单线程情况性能最好，随着线程数增加，性能越来越差，但是比cas高
    private static volatile int $synchronized = 0;
    private Object lock = new Object();

    //高并发下，cas性能最差
    public static volatile int cas = 0;

    //staticFieldOffset
    private static long casOffset;
    //指向 Unsafe->theUnsafe
    public static Unsafe UNSAFE;

    static {
        try {
            @SuppressWarnings("ALL")
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
            casOffset = UNSAFE.staticFieldOffset(MultiThreadTest.class.getDeclaredField("cas"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //乐观锁   调用unsafe类实现cas
    public void cas() {
        boolean bl = false;
        int tmp;
        while (!bl) {
            tmp = cas;
            bl = UNSAFE.compareAndSwapInt(MultiThreadTest.class, casOffset, tmp, tmp + 1);
        }
    }

    //模拟AtomicInteger的实现
    public void atomicInteger() {
        UNSAFE.getAndAddInt(this, casOffset, 1);
    }


    //对a执行+1操作，执行10000次
    public void run() {
        int i = 1;
        int count = 10000 /** 1000*/;
        /*
        while (i++ <= count) {
            //测试AtomicInteger
//            atomInteger.incrementAndGet();

            //atomicInteger实现;
//            atomicInteger();

            //测试LongAdder
//            longAdder.increment();

            //测试volatile和cas  乐观锁
//            cas();

            //测试锁
            synchronized (lock) {
                ++$synchronized;
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //100个线程
        for (int i = 1; i <= 60; i++) {
            new MultiThreadTest().start();
        }
        System.out.println("Thread.activeCount()=" + Thread.activeCount());
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("$synchronized:" + $synchronized);//277
        System.out.println("AtomicInteger:" + atomInteger.get());//126
        System.out.println("LongAdder:" + longAdder);//209
        System.out.println("cas:" + cas);//207
    }
*/
}
