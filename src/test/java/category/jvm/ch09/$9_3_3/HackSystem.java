package category.jvm.ch09.$9_3_3;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**********************************************************************
 * &lt;p&gt;文件名：HackSystem.java &lt;/p&gt;
 * &lt;p&gt;文件描述：用于替换System的输出，将测试类中每次System.out的内容输出到字节数组流中，最后一次性输出到页面(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2019/3/21 16:22
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class HackSystem {
    public final static InputStream in = System.in;
    private static ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    public static final PrintStream out = new PrintStream(buffer);
    public static final PrintStream err = out;

    public static String getBuffer() {
        return buffer.toString();
    }

    public static void clearBuffer() {
        buffer.reset();
    }
}
