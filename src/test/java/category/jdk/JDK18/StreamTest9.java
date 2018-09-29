package category.jdk.JDK18;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**********************************************************************
 * &lt;p&gt;文件名：null.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/9/29 00:09
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class StreamTest9 {
    /**
     * 方式1：
     * 自己实现的ForkJoin
     */
    @Test
    public void test1() {
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 100000000L);

        Long sum = pool.invoke(task);
        Instant end = Instant.now();

        System.out.println(Duration.between(start, end).toMillis());
    }

    public class ForkJoinCalculate extends RecursiveTask<Long> {

        private long start;
        private long end;

        private static final long THRESHHOLD = 10000;

        public ForkJoinCalculate(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long length = end - start;

            if (length <= THRESHHOLD) {
                long sum = 0;
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            } else {
                long middle = (start + end) / 2;

                ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
                left.fork();

                ForkJoinCalculate right = new ForkJoinCalculate(middle + 1, end);
                right.fork();

                return left.join() + right.join();
            }
        }
    }


    /**
     * 方式2：
     * 直接使用for循环
     */
    @Test
    public void test2() {
        Instant start = Instant.now();

        Long sum = 0L;
        for (int i = 0; i <= 100000000L; i++) {
            sum += i;
        }


        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());

    }

    /**
     * 方式3：
     * JDK8的并行流实现。
     */
    @Test
    public void test3() {
        Instant start = Instant.now();

        long sum = LongStream.rangeClosed(0, 1000000000L)
                .parallel()//并行流
                //.sequential()//串行流
                .reduce(0, Long::sum);

        Instant end = Instant.now();

        System.out.println(Duration.between(start, end).toMillis());
    }
//并行流将会充分使用多核的优势，多线程并行执行，基数越大，效果越明显。其底层还是Fork/Join框架。只不过SUN公司优化的更好，比自己实现更高效

}
