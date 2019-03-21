package category.book.shenrujvm.ch09.$9_3_3;

/**********************************************************************
 * &lt;p&gt;文件名：HotSwapClassLoader.java &lt;/p&gt;
 * &lt;p&gt;文件描述：测试类的类加载器，通过字节数组的方式进行加载(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2019/3/21 16:19
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }
}
