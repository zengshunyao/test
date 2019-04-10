package category.jdk.java18.parallestream;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**********************************************************************
 * &lt;p&gt;文件名：ParalleStream.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2019/4/4 22:33 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class UnsafeThreadParallelStreamTest {
    private static List<Integer> list1 = new ArrayList<Integer>();
    private static List<Integer> list2 = new ArrayList<Integer>();
    private static List<Integer> list3 = new ArrayList<Integer>();
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        //串行
        IntStream.range(0, 10000).forEach(list1::add);

        //并行
        IntStream.range(0, 10000).parallel().forEach(list2::add);

        IntStream.range(0, 10000).forEach(i -> {
            lock.lock();
            try {
                //串行
                list3.add(i);
                System.out.println(Thread.currentThread().getName());
            } finally {
                lock.unlock();
            }
        });

        System.out.println("串行执行的大小：" + list1.size());
        System.out.println("并行执行的大小：" + list2.size());
        System.out.println("加锁并行执行的大小：" + list3.size());

//        并且每次的结果中并行执行的大小不一致，而串行和加锁后的结果一直都是正确结果。显而易见，stream.parallel.forEach()中执行的操作并非线程安全。
//        那么既然paralleStream不是线程安全的，是不是在其中的进行的非原子操作都要加锁呢？我在stackOverflow上找到了答案：
//        https://codereview.stackexchange.com/questions/60401/using-java-8-parallel-streams
//        https://stackoverflow.com/questions/22350288/parallel-streams-collectors-and-thread-safety
//        在上面两个问题的解答中，证实paralleStream的forEach接口确实不能保证同步，同时也提出了解决方案：使用collect和reduce接口。
//        http://docs.oracle.com/javase/tutorial/collections/streams/parallelism.html
//        在Javadoc中也对stream的并发操作进行了相关介绍：
//        The Collections Framework provides synchronization wrappers, which add automatic synchronization to an arbitrary collection, making it thread-safe.
//                Collections框架提供了同步的包装，使得其中的操作线程安全。
    }
}
