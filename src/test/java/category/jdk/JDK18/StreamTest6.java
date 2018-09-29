package category.jdk.JDK18;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**********************************************************************
 * &lt;p&gt;文件名：null.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/9/29 21:09
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class StreamTest6 {

    /**
     * 终止操作：
     * <p>
     * 归约
     * reduce(T identity, BinaryOperator) / reduce(BinaryOperator)
     * -- 可以将流中的元素反复结合起来，得到一个值
     */
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer reduce = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);

        Optional<Integer> reduce1 = emps.stream()
                .map(Employee::getSalary)//现将salsry映射出来
                .reduce(Integer::sum);//进行归约求和

        System.out.println(reduce1.get());
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
