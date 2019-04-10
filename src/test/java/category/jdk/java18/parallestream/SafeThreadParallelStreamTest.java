package category.jdk.java18.parallestream;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
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
public class SafeThreadParallelStreamTest {
    private List<Integer> nums = new ArrayList<Integer>();
    private Set<Double> result = new HashSet<Double>();

    public static void main(String[] args) {
        SafeThreadParallelStreamTest main = new SafeThreadParallelStreamTest();
        main.run();

        System.out.println("串行执行的大小：" + main.nums.size());

//        System.out.println("原始数据：");
//        main.nums.forEach(i -> System.out.print(i + " "));

        System.out.println("并行执行的大小：" + main.result.size());

//        System.out.println("\n\ncollect方法加工后的数据：");
//        main.result.forEach(i -> System.out.print(i + " "));
    }

    public void run() {
        // 填充原始数据，nums中填充0-9999 10000个数
        IntStream.range(0, 10000).forEach(nums::add);
        //实现Collector接口
        result = nums.stream().parallel().collect(new Collector<Integer, Container, Set<Double>>() {

            @Override
            public Supplier<Container> supplier() {
                return Container::new;
            }

            @Override
            public BiConsumer<Container, Integer> accumulator() {
                return Container::accumulate;
            }

            @Override
            public BinaryOperator<Container> combiner() {
                return Container::combine;
            }

            @Override
            public Function<Container, Set<Double>> finisher() {
                return Container::getResult;
            }

            @Override
            public Set<Characteristics> characteristics() {
                // 固定写法
                return Collections.emptySet();
            }
        });
    }

    static class Container {
        // 定义本地的result
        public Set<Double> results;

        public Container() {
            this.results = new HashSet<>();
        }

        public Container accumulate(int num) {
            this.results.add(Compute.compute(num));
            return this;
        }

        public Container combine(Container container) {
            this.results.addAll(container.results);
            return this;
        }

        public Set<Double> getResult() {
            return this.results;
        }
    }

    static class Compute {
        public static Double compute(int num) {
            return (double) (2 * num);
        }
    }
}
