package category.book.shenrujvm.ch09.$9_3_3;

import java.lang.reflect.Method;

/**********************************************************************
 * &lt;p&gt;文件名：JavaClassExecuter.java &lt;/p&gt;
 * &lt;p&gt;文件描述：执行类，通过反射调用测试类中的main方法，最后取出HackSystem中字节数组流中的数据进行返回(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2019/3/21 16:24
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class JavaClassExecuter {

    public static String executer(byte[] classByte) throws NoSuchMethodException {
        HackSystem.clearBuffer();
        ClassModifier classModifier = new ClassModifier(classByte);
        byte[] modiByte = classModifier.modiftyUTF8Constant("java/lang/System", "org/jvm/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class cs = loader.loadByte(modiByte);
        try {
            Method method = cs.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        } catch (Throwable throwable) {
            throwable.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBuffer();
    }
}
