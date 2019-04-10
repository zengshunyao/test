package category.jdk.java18;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**********************************************************************
 * &lt;p&gt;文件名：null.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/9/29 20:27
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class StreamTest3 {

    //中间操作

    /**
     * 映射
     * map -- 接受Lambda，将元素转换成其他形式或提取信息，接受一个函数作为参数，
     * 该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * <p>
     * flatmap -- 接受一个函数做为参数，将流中的每个值都转换成另一个流，然后将所有流连接成一个流，
     */

    final List<Employee> emps = Arrays.asList(
            new Employee("张三", "上海", 5000, 22),
            new Employee("李四", "北京", 4000, 23),
            new Employee("c五", "日本", 6000, 50),
            new Employee("b七", "香港", 7000, 50),
            new Employee("赵六", "纽约", 1000, 8)
    );

    /**
     * 映射
     */
    @Test
    public void test2() {
        emps.stream()//创建流
                .map(employee -> employee.getName())//中间操作：映射
                .forEach(System.out::println);//终止流
    }

    /**
     * 流中流
     */
    @Test
    public void test3() {
        final List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        //1.流中还是流
        Stream<Stream<Character>> streamStream = list.stream().map(StreamTest3::getCharacter);//流中还是流

        streamStream.forEach(sm -> sm.forEach(System.out::print));
        System.out.println();

        System.out.println("-------------------------------------");

        //2.流中还是流 链式编程
        list.stream().map(StreamTest3::getCharacter).forEach(sm -> sm.forEach(System.out::print));
        System.out.println();

        System.out.println("-------------------------------------");

        //3.大流中直接包含流元素
        list.stream()
                .flatMap(StreamTest3::getCharacter)//大流中直接包含的是流元素，相当于add和addAll的区别。
                .forEach(System.out::print);
        System.out.println();

        System.out.println("-------------------------------------");

        //3.大流中直接包含流元素
        list.stream().flatMap(str -> {
            final List<Character> charList = new LinkedList<>();
            for (char c : str.toCharArray()) {
                charList.add(c);
            }
            return charList.stream();
        })//大流中直接包含的是流元素，相当于add和addAll的区别。
                .forEach(System.out::print);
        System.out.println();
    }

    public static Stream<Character> getCharacter(final String str) {
        final List<Character> list = new LinkedList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }
}
