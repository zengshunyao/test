package category.jdk.JDK18;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**********************************************************************
 * &lt;p&gt;文件名：null.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/9/29 17:37
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class LambdaTest {
    public static void main(String[] args) {
        //        语法格式一：无参数，无返回值
        Runnable r2 = () -> System.out.println("hello lambda");
        r2.run();


    }

    public static void test() {
        //语法格式二：有一个参数，并且无返回值
//        (x) -> System.out.print(x);
        //语法格式三：若只有一个参数，小括号可以省略不写
//        x -> System.out.print(x);
    }

    public static void comp(String[] args) {
        //语法格式五：若Lambda体中只有一条语句，return和大括号都可以省略不写
        Comparator<Integer> c1 = (x, y) -> {
            System.out.print(Integer.compare(x, y) + "函数式接口");
            return Integer.compare(x, y);
        };
        c1.compare(1, 2);

        //语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM编译器可以通过上下文进行类型推断出数据类型，既“类型推断”。
        Comparator<Integer> c2 = (x, y) -> Integer.compare(x, y);

//        总结：
//左右遇一括号省，左侧推断类型省， 能省则省。
    }

    //    2、Lambda表达式的函数式接口
//一些基本的使用示例
    List<Employee> list = Arrays.asList(
            new Employee("张三", "上海", 5000, 22),
            new Employee("李四", "北京", 4000, 23),
            new Employee("c五", "日本", 6000, 50),
            new Employee("b七", "香港", 7000, 50),
            new Employee("赵六", "纽约", 1000, 8)
    );

    /**
     * 需求1：lambda表达式的使用:
     * 调用COllections.sort方法，通过定制排序比较两个Employee（先按年龄比较，年龄相同按姓名比），使用
     * Lambda作为参数传递。
     */
    @Test
    public void test1() {
        Collections.sort(list, (x, y) -> {
            if (x.getAge() != y.getAge())
                return Integer.compare(x.getAge(), y.getAge());
            else
                return x.getName().compareTo(y.getName());

        });

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    /**
     * 需求2：
     * 1.声明函数式接口，接口中声明抽象方法，public String getvalue(String str();
     * 2.声明类TestLambda，类中编写方法使用接口作为参数，讲一个字符串转换成大写，并作为方法的返回值。
     */
    @Test
    public void test2() {
        String str = getvalue("hello world", x -> x.toUpperCase());
        System.out.print(str);

    }

    public String getvalue(String str, MyFunction1 my) {
        return my.getValue(str);
    }

    public String calc(int num1, int num2) {
        return null;
    }

    @FunctionalInterface
    public interface MyFunction1 {
        String getValue(String str);

        default String calc(int num1, int num2) {
            return null;
        }
    }


    /**
     * 需求3：
     * 1.声明一个带两个泛型的函数式接口，泛型类型是<T,R>,T为参数，R为返回值。
     * 2.接口中声明对应抽象方法
     * 3.在TestLambda类中声明方法，使用接口作为参数，计算两个long型参数的和
     * 4.在计算两个long型参数的乘积
     */
    @Test
    public void test3() {
        Long r = getR(25l, 30l, (t1, t2) -> t1 * t2);
        System.out.print(r);

        Long r1 = getR(25l, 23l, (t1, t2) -> t1 + t2);
        System.out.print(r1);

    }

    public <T, R> R getR(T t1, T t2, MyFUnction2<T, R> mf) {
        return mf.method(t1, t2);
    }

    public interface MyFUnction2<T, R> {
        R method(T t1, T t2);
    }

}
