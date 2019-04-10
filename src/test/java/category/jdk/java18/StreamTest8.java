package category.jdk.java18;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**********************************************************************
 * &lt;p&gt;文件名：null.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/9/29 11:09
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class StreamTest8 {

    final List<Employee> emps = Arrays.asList(
            new Employee("张三", 9000, 25, Employee.Status.FREE),
            new Employee("李四", 16000, 22, Employee.Status.BUSY),
            new Employee("王五", 15000, 22, Employee.Status.FREE),
            new Employee("王五", 15000, 92, Employee.Status.FREE),
            new Employee("赵六", 12000, 23, Employee.Status.VOCATION),
            new Employee("孙七", 8000, 26, Employee.Status.FREE),
            new Employee("唐八", 19000, 24, Employee.Status.BUSY)
    );

    @Test
    public void test1() {
        /**
         * 需求1：
         *给定一个数字，如何返回一个有每个数的平方构成的列表？
         * 给定【1,2,3,4,5】，返回【1,4,9,16,25】
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collect = list.stream().map(integer -> integer * integer).collect((Collectors.toList()));
        System.out.println(collect);

        /**
         * 需求2：
         * 用reduce和map数一数流中的元素个数
         */
        Optional<Integer> reduce = list.stream()
                .map(e -> 1)//巧妙之处
                .reduce(Integer::sum);
        System.out.println(reduce);
    }

    /**
     * 收集
     * collect
     * -- 将流转换成其他的形式，接收一个Collector接口的实现，可以通过Collectors的实用类操作
     */
    @Test
    public void test2() {
        //收集姓名到列表
        List<String> collect = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("-------------------------");

        //收集姓名到set
        Set<String> collect1 = emps.stream().map(Employee::getName).collect(Collectors.toSet());
        collect1.forEach(System.out::println);
        System.out.println("--------------------------");

        //收集姓名到指定的数据结构
        LinkedHashSet<String> collect2 = emps.stream().map(Employee::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        collect2.forEach(System.out::println);
    }

    @Test
    public void test3() {
        //总数
        Long collect = emps.stream().collect(Collectors.counting());
        System.out.println(collect);
        System.out.println("---------------------------------");

        //平均
        Double collect1 = emps.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect1);
        System.out.println("-------------------------------");

        //总和
        Double collect2 = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collect2);
        System.out.println("-----------------------");

        //最大值
//        Optional<Employee> collect3 = emps.stream().collect(Collectors.maxBy((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary())));
        Optional<Employee> collect3 = emps.stream().collect(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)));
        System.out.println(collect3);
        System.out.println("-----------------------");
    }

    //分组
    @Test
    public void test4() {
        //单级分组
        Map<Employee.Status, List<Employee>> collect = emps.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(collect);
        System.out.println("----------------------");

        //多级分组
        Map<Employee.Status, Map<String, List<Employee>>> collect1 = emps.stream().collect(
                Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (e.getAge() < 20)
                        return "少年";
                    else if (e.getAge() < 30)
                        return "青年";
                    else
                        return "中年";
                })));
        System.out.println(collect1);
        System.out.println("----------------------");

        Map<Employee.Status, Map<String, List<Employee>>> collectX = emps.stream().collect(
                Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (e.getSalary() > 10000) {
                        return "高收入";
                    }
                    return "低收入";
                })));
        System.out.println(collectX);
        System.out.println("----------------------");

        //分区--满足条件一个区，不满足另一个区
        Map<Boolean, List<Employee>> collect2 = emps.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        System.out.println(collect2);
        System.out.println("-----------------------");

        //收集各种统计数据
        DoubleSummaryStatistics collect3 = emps.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect3 + "-----------平均薪水" + collect3.getAverage());

        //连接字符串
        String collect4 = emps.stream().map(Employee::getName).collect(Collectors.joining(",", "-----", "-----"));
        System.out.println(collect4);

    }

}
