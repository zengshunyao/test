package category.jdk.java18;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
public class StreamTest5 {
    //Stream的终止操作
    //- allMatch – 检查是否匹配所有元素
    //- anyMatch – 检查是否至少匹配一个元素
    //- noneMatch – 检查是否没有匹配所有元素
    //- findFirst – 返回第一个元素
    //- count – 返回流中元素的总个数
    //- max – 返回流中最大值
    //- min – 返回流中最小值

    @Test
    public void test1() {
        //检查是否匹配所有元素
        boolean b = emps.stream()
                .allMatch(emp -> emp.getStatus().equals(Employee.Status.FREE));
        System.out.println(b);

        //检查是否至少匹配一个元素
        boolean b1 = emps.stream().
                anyMatch(emp -> emp.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        //检查是否没有匹配所有元素
        boolean b2 = emps.stream()
                .noneMatch(emp -> emp.getSalary() > 16000);
        System.out.println(b2);

        //返回第一个元素
        Optional<Employee> firstEmployee = emps.stream()
                .filter(emp -> emp.getSalary() > 16000)
                .findFirst();
        System.out.println(firstEmployee);

        //返回流中元素的总个数
        long count = emps.stream()
                .filter(emp -> emp.getSalary() > 16000)
                .count();
        System.out.println(count);

        //返回流中最大值
        Optional<Employee> maxEmployee = emps.stream()
                .filter(emp -> emp.getSalary() > 16000)
                .max((e1, e2) -> {
                    if (e1.getSalary() == e2.getSalary()) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return e1.getSalary() - e2.getSalary();
                    }
                });
        System.out.println(maxEmployee);

        //返回流中最小值
        Optional<Employee> minEmployee = emps.stream()
                .filter(emp -> emp.getSalary() > 16000)
                .min((e1, e2) -> {
                    if (e1.getSalary() == e2.getSalary()) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return e1.getSalary() - e2.getSalary();
                    }
                });
        System.out.println(minEmployee);

        //并行流
        Optional<Employee> any = emps.parallelStream()
                .findAny();
        System.out.println(any.get());
    }

    final List<Employee> emps = Arrays.asList(
            new Employee("张三", 9000, 25, Employee.Status.FREE),
            new Employee("李四", 16000, 22, Employee.Status.BUSY),
            new Employee("王五", 15000, 22, Employee.Status.FREE),
            new Employee("赵六", 12000, 23, Employee.Status.VOCATION),
            new Employee("孙七", 8000, 26, Employee.Status.FREE),
            new Employee("唐八", 19000, 24, Employee.Status.BUSY)
    );

}
