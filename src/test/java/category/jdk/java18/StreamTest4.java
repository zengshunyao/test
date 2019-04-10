package category.jdk.java18;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**********************************************************************
 * &lt;p&gt;文件名：null.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/9/29 20:41
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class StreamTest4 {
    //中间操作

    /**
     * 排序
     * <p>
     * sorted -- 自然排序(Comparable)
     * sorted(Comparator com) -- 定制排序(Comparator)
     */

    @Test
    public void test1() {
        List<String> list =
                Arrays.asList("ddd", "ccc", "ggg", "bbb", "aaa");

        list.stream()
                .sorted()//自然排序
                .forEach(System.out::println);

        System.out.println("------------------------------");

        emps.stream().sorted((e1, e2) -> {//定制排序
            if (e1.getSalary() == e2.getSalary()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return e1.getSalary() - e2.getSalary();
            }
        }).forEach(System.out::println);

    }


    final List<Employee> emps = Arrays.asList(
            new Employee("张三", "上海", 5000, 22),
            new Employee("李四", "北京", 4000, 23),
            new Employee("c五", "日本", 6000, 50),
            new Employee("b七", "香港", 6000, 50),
            new Employee("赵六", "纽约", 1000, 8)
    );

}
