package category.book.shenrujvm.ch08.$8_3_3;

import java.lang.invoke.*;

/**********************************************************************
 * &lt;p&gt;文件名：InvokeDynamicTest.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2019/3/21 22:01 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class InvokeDynamicTest {
    public static void main(String[] args) throws Throwable {
//        MethodHandle mh = INDY_BootstrapMethod();
//        mh.invoke("icyfenix");

        MethodHandle mh = INDY_BootstrapMethod();
        mh.invoke("icyfenix");
        mh.invokeExact("icyfenix");
    }


    public static CallSite BootstrapMethod(MethodHandles.Lookup lookup,
                                           String name,
                                           MethodType mt,
                                           Class<?> claess) throws Throwable {
        return new ConstantCallSite(lookup.findStatic(claess, name, mt));
    }


    // 动态描述参数类型
    private static MethodType MT_BootstrapMethod() {
        MethodType mt = MethodType.fromMethodDescriptorString(
                "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/Class;)Ljava/lang/invoke/CallSite;",
                null);
        return mt;
    }

    // 通过MethodHandle动态加载调用CallSite BootstrapMethod(...)方法
    private static MethodHandle MH_BootstrapMethod() throws Throwable {
        MethodType mt = MT_BootstrapMethod();
        MethodHandle mh = MethodHandles.lookup().findStatic(InvokeDynamicTest.class, "BootstrapMethod", mt);
        return mh;
    }

    private static MethodHandle INDY_BootstrapMethod() throws Throwable {

        MethodType mt = MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null);

        MethodHandle mh = MH_BootstrapMethod();

        CallSite cs = (CallSite) mh.invokeWithArguments(
                MethodHandles.lookup(),
                "testMethod",
                mt,
                MethodHandlerTest.ClassA.class);


        return cs.dynamicInvoker();
    }


//    public static void testMethod(String s) {
//        System.out.println("hello world:" + s);
//    }
}
