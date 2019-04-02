package category.book.shenrujvm.ch08.$8_3_3.ext;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**********************************************************************
 * &lt;p&gt;文件名：MethodHandleTest.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2019/3/21 23:15 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class MethodHandleTest {
    static class ClassA {
        public void println() {
            System.out.println("this is A");
        }
    }

    static class ClassB extends ClassA {
        public void println() {
            System.out.println("this B");
        }
    }

    public static void test2() throws Throwable {
        Object obj = new ClassA();
//        Object obj = new ClassB();
        MethodType mt = MethodType.methodType(void.class);
        MethodHandle method = MethodHandles.lookup().findVirtual(obj.getClass(), "println", mt).bindTo(obj);
//        MethodHandle method = MethodHandles.lookup().findVirtual(ClassA.class, "println", mt).bindTo(obj);

        method.invokeExact();
    }

    public static void main(String[] args) throws Throwable {
        test2();
    }
}
