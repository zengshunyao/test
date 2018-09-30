package category.jdk.JDK18;

import org.junit.Test;

import java.util.Optional;

/**********************************************************************
 * &lt;p&gt;文件名：null.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/9/29 2:09
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class OptinalTest1 {
    /**
     * 常用方法：
     * Optional.of(T t) : 创建一个Optional 实例
     * Optional.empty() : 创建一个空的Optional 实例
     * Optional.ofNullable(T t):若t 不为null,创建Optional 实例,否则创建空实例
     * isPresent() : 判断是否包含值
     * orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
     * orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回s 获取的值
     * map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
     * flatMap(Function mapper):与map 类似，要求返回值必须是Optional
     */

    /**
     * of 构建 创建一个Optional 实例
     */
    @Test
    public void test1() {
        final Optional<Employee> op = Optional.of(new Employee());
        final Employee employee = op.get();
        System.out.println(employee);

        //直接传null会发生空指针异常
//        final Optional<Object> op2 = Optional.of(null);
//        final Object o = op2.get();
//        System.out.println(o);
    }

    /**
     * empty 创建一个空的Optional 实例
     */
    @Test
    public void test2() {
        final Optional<Object> op = Optional.empty();
        //也会报错，NoSuchElement.Exception
        System.out.println(op.get());
    }

    /**
     * ofNullable 若t 不为null,创建Optional 实例,否则创建空实例
     */
    @Test
    public void test3() {
        final Optional<Object> op = Optional.ofNullable(null);
        if (op.isPresent()) {
            //仍然会报错，NoSUchELEMEnt.exception
            System.out.println(op.get());
        } else
            System.out.println("No Value");
    }

    /**
     * orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
     * orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回s 获取的值
     */
    @Test
    public void test4() {
        final Optional<Employee> op = Optional.ofNullable(null);
        if (op.isPresent()) {
            System.out.println(op.get());
        } else {
            //如果没值，传入默认的值
//            Employee o = op.orElse(new Employee());
// 函数式接口，可以写更多
            Employee o = op.orElseGet(Employee::new);
            System.out.println(o);
        }
    }

    /**
     * map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
     */
    @Test
    public void test5() {
        final Optional<Employee> op = Optional.ofNullable(new Employee("jim", "shanghai", 9000, 25));
//        Optional<Employee> op = Optional.ofNullable(null);

        //有值返回map的操作，没值返回Optional.empty()
        Optional<Integer> salary = op.map(Employee::getSalary);
        System.out.println(salary.get());//如果传入为空，此时会报错
    }

    /**
     * flatMap(Function mapper):与map 类似，要求返回值必须是Optional
     */
    @Test
    public void test6() {
//        final Optional<Employee> op = Optional.ofNullable(new Employee("jim", "shanghai", 9000, 26));
        Optional<Employee> op = Optional.ofNullable(null);
        //flatMap，返回的必须是Optional容器，进一步避免空指针异常
        Optional<Integer> optional = op.flatMap(e -> Optional.of(e.getSalary()));

        //二选一
//        System.out.println(optional.orElse(10000));
        System.out.println(optional.orElseGet(new Employee()::getSalary));
    }
}
