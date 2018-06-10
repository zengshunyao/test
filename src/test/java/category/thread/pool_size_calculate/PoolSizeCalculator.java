package category.thread.pool_size_calculate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(如何合理地估算线程池大小？)
 * @link( http://ifeve.com/how-to-calculate-threadpool-size/)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/18 21:53
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public abstract class PoolSizeCalculator {
    /**
     * The sample queue size to calculate the size of a single {@link Runnable} element.
     */
    private final int SAMPLE_QUEUE_SIZE = 1000;

    /**
     * Accuracy of test run. It must finish within 20ms of the testTime
     * otherwise we retry the test. This could be configurable.
     */
    private final int EPSYLON = 20;

    /**
     * Control variable for the CPU time investigation.
     */
    private volatile boolean expired;

    /**
     * Time (millis) of the test run in the CPU time calculation.
     */
    private final long testTime = 3000;

    /**
     * Calculates the boundaries of a thread pool for a given {@link Runnable}.
     *
     * @param targetUtilization    the desired utilization of the CPUs (0 <= targetUtilization <= 1)
     * @param targetQueueSizeBytes the desired maximum work queue size of the thread pool (bytes)
     */
    protected void calculateBoundaries(BigDecimal targetUtilization, BigDecimal targetQueueSizeBytes) {
        this.calculateOptimalCapacity(targetQueueSizeBytes);

        Runnable task = createTask();
        start(task);
        start(task);
        // warm up phase
        long cpuTime = getCurrentThreadCPUTime();
        start(task);
        // test interval
        cpuTime = getCurrentThreadCPUTime() - cpuTime;
        long waitTime = (testTime * 1000000) - cpuTime;
        this.calculateOptimalThreadCount(cpuTime, waitTime, targetUtilization);
    }

    /**
     * @param targetQueueSizeBytes
     */
    private void calculateOptimalCapacity(BigDecimal targetQueueSizeBytes) {
        //获得内存使用量 ,in bytes.
        long mem = calculateMemoryUsage();
        //  建议队列容量（字节）
        BigDecimal queueCapacity = targetQueueSizeBytes.divide(new BigDecimal(mem), RoundingMode.HALF_UP);
        System.out.println("------------------calculateOptimalCapacity-----------------------");
        System.out.println("Target queue memory usage (bytes): " + targetQueueSizeBytes);
        System.out.println("createTask() produced " + createTask().getClass().getName() + " which took " + mem + " bytes in a queue");
        System.out.println("Formula: " + targetQueueSizeBytes + " / " + mem);
        System.out.println("* Recommended queue capacity (bytes): " + queueCapacity);
        System.out.println("------------------calculateOptimalCapacity-----------------------");
    }

    /**
     * Brian Goetz' optimal thread count formula, see 'Java Concurrency in
     * Practice' (chapter 8.2)
     *
     * @param cpu               cpu time consumed by considered task
     * @param wait              wait time of considered task
     * @param targetUtilization target utilization of the system
     */
    private void calculateOptimalThreadCount(long cpu, long wait, BigDecimal targetUtilization) {
        BigDecimal waitTime = new BigDecimal(wait);
        BigDecimal computeTime = new BigDecimal(cpu);

        BigDecimal numberOfCPU = new BigDecimal(Runtime.getRuntime().availableProcessors());
        // 如果是CPU密集型应用，则线程池大小设置为N+1
        // 如果是IO密集型应用，则线程池大小设置为2N+1
        //服务器性能IO优化 中发现一个估算公式：
        //最佳线程数目 = （（线程等待时间+线程CPU时间）/线程CPU时间 ）* CPU数目
        //最佳线程数目 = （线程等待时间与线程CPU时间之比 + 1）* CPU数目
        //(cpu数量*1)+(等待时间/计算时间)
        BigDecimal optimalThreadCount = numberOfCPU.multiply(targetUtilization)
                .multiply(new BigDecimal(1)
                        .add(waitTime.divide(computeTime, RoundingMode.HALF_UP)));
        System.out.println("------------------calculateOptimalThreadCount-----------------------");
        System.out.println("Number of CPU: " + numberOfCPU);
        System.out.println("Target utilization: " + targetUtilization);
        System.out.println("Elapsed time (nanos): " + (testTime * 1000000));
        System.out.println("Compute time (nanos): " + cpu);
        System.out.println("Wait time (nanos): " + wait);
        System.out.println("Formula: " + numberOfCPU + " * " + targetUtilization + " * (1 + " + waitTime + " / " + computeTime + ")");
        System.out.println("* Optimal thread count: " + optimalThreadCount);
        //可以得出一个结论：
        // 线程等待时间所占比例越高，需要越多线程。线程CPU时间所占比例越高，需要越少线程。
        System.out.println("------------------calculateOptimalThreadCount-----------------------");
    }

    /**
     * Runs the {@link Runnable} over a period defined in {@link #testTime}.
     * Based on Heinz Kabbutz' ideas
     * (http://www.javaspecialists.eu/archive/Issue124.html).
     *
     * @param task the runnable under investigation
     */
    public void start(Runnable task) {
        long start = 0L;
        int runs = 0;
        //花费时间 毫秒
        long spend = 0L;
        do {
            if (++runs > 50) {
                throw new IllegalStateException("Test not accurate");
            }
            expired = false;
            start = System.currentTimeMillis();
            Timer timer = new Timer();
            //设置任务3秒后执行
            timer.schedule(new TimerTask() {
                public void run() {
                    expired = true;
//                    System.out.println("输出---");
                }
            }, testTime);

            while (!expired) {
                task.run();
            }

            spend = System.currentTimeMillis() - start;
            timer.cancel();
        } while (Math.abs(spend - testTime) > EPSYLON);

        collectGarbage(3);
    }

    /**
     * 垃圾回收
     *
     * @param times 次数
     */
    private void collectGarbage(int times) {
        for (int i = 0; i < times; i++) {
            System.gc();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Calculates the memory usage of a single element in a work queue. Based on
     * Heinz Kabbutz' ideas
     * (http://www.javaspecialists.eu/archive/Issue029.html).
     *
     * @return memory usage of a single {@link Runnable} element in the thread
     * pools work queue , measured in bytes.
     */
    public long calculateMemoryUsage() {

        //创建任务队列
        BlockingQueue queue = createWorkQueue();
        //创建任务并装入队列
        for (int i = 0; i < SAMPLE_QUEUE_SIZE; i++) {
            queue.add(createTask());
        }
        long mem0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long mem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        queue = null;
        collectGarbage(15);
        mem0 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        //再次创建任务队列
        queue = createWorkQueue();
        //创建任务并装入队列
        for (int i = 0; i < SAMPLE_QUEUE_SIZE; i++) {
            queue.add(createTask());
        }

        collectGarbage(15);
        mem1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        return (mem1 - mem0) / SAMPLE_QUEUE_SIZE;
    }

    /**
     * Create your runnable task here.
     *
     * @return an instance of your runnable task under investigation
     */
    protected abstract Runnable createTask();

    /**
     * Return an instance of the queue used in the thread pool.
     *
     * @return queue instance
     */
    protected abstract BlockingQueue createWorkQueue();

    /**
     * Calculate current cpu time. Various frameworks may be used here,
     * depending on the operating system in use. (e.g.
     * http://www.hyperic.com/products/sigar). The more accurate the CPU time
     * measurement, the more accurate the results for thread count boundaries.
     *
     * @return current cpu time of current thread
     */
    protected abstract long getCurrentThreadCPUTime();
}
