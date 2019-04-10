package category.jdk.java17;

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
        final Instant start = Instant.now();
        final ForkJoinPool pool = new ForkJoinPool();
        final ForkJoinTask<Long> task = new ForkJoinCalculate(0, 100000000L);

        final Long sum = pool.invoke(task);
        final Instant end = Instant.now();

        System.out.println("花费时间：" + Duration.between(start, end).toMillis()
                + "ms,计算结果" + sum);
    }


    private static class ForkJoinCalculate extends RecursiveTask<Long> {

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
        System.out.println("花费时间：" + Duration.between(start, end).toMillis()
                + "ms,计算结果" + sum);
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

//        long sum = LongStream.rangeClosed(0, 100000000L).sequential().reduce(0, Long::sum);

        Instant end = Instant.now();

        System.out.println("花费时间：" + Duration.between(start, end).toMillis()
                + "ms,计算结果" + sum);
    }
//并行流将会充分使用多核的优势，多线程并行执行，基数越大，效果越明显。其底层还是Fork/Join框架。
// 只不过SUN公司优化的更好，比自己实现更高效

    public static void main(String[] args) {
        StreamTest9 test = new StreamTest9();
        System.out.println("--------自己实现多线程--------");
        test.test1();
        System.out.println("--------单线程--------");
        test.test2();
        System.out.println("--------jdk并行线程--------");
        test.test3();
    }
}
