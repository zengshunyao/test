package category.book.shenrujvm.ch08.$8_3_3.ext;

import java.lang.invoke.*;

/**********************************************************************
 * &lt;p&gt;文件名：DynamicTest.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2019/3/21 23:21 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class DynamicTest {
    public static void test() {
        System.out.println("成功");
    }

    public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name, MethodType mt) throws Throwable {
        return new ConstantCallSite(lookup.findStatic(DynamicTest.class, name, mt));
    }

    public static MethodType getMethodType() {
        return MethodType.fromMethodDescriptorString(
                "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
                null);
    }

    public static MethodHandle getMethodHandle() throws Throwable {
        return MethodHandles.lookup().findStatic(DynamicTest.class, "BootstrapMethod", getMethodType());
    }

    private static MethodHandle INDY_BootstrapMethod() throws Throwable {
        CallSite cs = (CallSite) getMethodHandle().invokeWithArguments(
                MethodHandles.lookup(),
                "test",
                MethodType.fromMethodDescriptorString("()V", null)
        );
        return cs.dynamicInvoker();
    }

    public static void main(String[] args) throws Throwable {
        MethodHandle mh = INDY_BootstrapMethod();
        mh.invokeExact();
    }
}
