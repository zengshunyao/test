package category.book.shenrujvm.ch08.$8_3_3;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**********************************************************************
 * &lt;p&gt;文件名：MethodHandlerTest.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2019/3/21 22:08 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class MethodHandlerTest {
    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }

        public static void testMethod(String s) {
            System.out.println("hello world:" + s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        //无论obj最终是哪个实现类，下面这句都能正确调用到println方法
        getPrintlnMH(obj).invokeExact("icyfenix");
    }

    public static MethodHandle getPrintlnMH(Object reveiver) throws NoSuchMethodException, IllegalAccessException {
        /*MethodType:代表“方法类型”，包含了方法的返回值（methodType()的第一个参数 > 和 具体参数（methodType()第二个及以后的参数）*/
        MethodType mt = MethodType.methodType(void.class, String.class);
        // 这个是方法类型，第一个参数是返回类型，第二个参数是我们的参数类型，后面还有可以有其他的类型

        /*lookup()方法来自于MethodHandles.lookup,这句的作用是在指定类中查找符合给定的 方法名称、方法类型，并且符合调用权限的方法句柄*/
        /*因为这里调用的是一个虚方法，按照Java语言的规則，方法第一个参数是隐式的.代表该方 法的接收者，也即是this指向的对象，
          这个参数以前是放在参教列表中进行传递的，而现在提供了 bindTo()方 法来完成这件車情*/
        return MethodHandles.lookup().findVirtual(reveiver.getClass(), "println", mt).bindTo(reveiver);
        // 这个lookup方法中find是用来查找我们指定的类里面时候含有这个println这个类和相对应的方法类型，然后我们的bindTo是返回这个方法的MethodHandle句柄
    }
}
