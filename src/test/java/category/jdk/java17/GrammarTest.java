package category.jdk.java17;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**********************************************************************
 * &lt;p&gt;文件名：null.java &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/9/29 17:16
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class GrammarTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    //2.泛型实例化类型自动推断
    ArrayList<String> al1 = new ArrayList<String>();    // Old
    ArrayList<String> al2 = new ArrayList<>();           // New

    // 3.新的整数字面表达方式 - "0b"前缀和"_"连数符
    // a. 表示二进制字面值的前缀0b。
    byte b1 = 0b00100001;     // New
    byte b2 = 0x21;        // Old
    byte b3 = 33;        // Old

    //b. 字面常量数字的下划线。用下划线连接整数提升其可读性，自身无含义，不可用在数字的起始和末尾。
    //
    //Java编码语言对给数值型的字面值加下划线有严格的规定。如上所述，你只能在数字之间用下划线。你不能用把一个数字用下划线开头，或者已下划线结尾。这里有一些其它的不能在数值型字面值上用下划线的地方：
    //
    //在数字的开始或结尾
    //对浮点型数字的小数点附件
    //F或L下标的前面
    //该数值型字面值是字符串类型的时候

    //float pi1 = 3_.1415F; // 无效的; 不能在小数点之前有下划线
    //float pi2 = 3._1415F; // 无效的; 不能在小数点之后有下划线
//    long socialSecurityNumber1 = 999_99_9999_L;//无效的，不能在L下标之前加下划线
//    int a1 = _52; // 这是一个下划线开头的标识符，不是个数字
    int a2 = 5_2; // 有效
    //    int a3 = 52_; // 无效的，不能以下划线结尾
    int a4 = 5_______2; // 有效的
    //    int a5 = 0_x52; // 无效，不能在0x之间有下划线
//    int a6 = 0x_52; // 无效的，不能在数字开头有下划线
    int a7 = 0x5_2; // 有效的 (16进制数字)
    //    int a8 = 0x52_; // 无效的，不能以下划线结尾
    int a9 = 0_52; // 有效的（8进制数）
    int a10 = 05_2; // 有效的（8进制数）
//    int a11 = 052_; // 无效的，不能以下划线结尾

    //4.在单个catch代码块中捕获多个异常，以及用升级版的类型检查重新抛出异常
    //
    public void testBeforeJdk17() {
        try {
            FileInputStream file = new FileInputStream("");
            file.read();
        } catch (IOException e) {
            logger.error("error", e);
            throw new IllegalStateException(e.getMessage());
        } catch (Exception e) {
            logger.error("error", e);
            throw new IllegalStateException(e.getMessage());
        }
    }

    public void testAboveJdk17() {
        try {
            FileInputStream file = new FileInputStream("");
            file.read();
        } catch (IllegalArgumentException | IOException e) {
            logger.error("error", e);
            throw new IllegalStateException(e.getMessage());
        }
    }

    //5.try-with-resources语句
    //try-with-resources语句是一个声明一个或多个资源的try语句。一个资源作为一个对象，必须在程序结束之后关闭。try-with-resources语句确保在语句的最后每个资源都被关闭，任何实现了Java.lang.AutoCloseable和java.io.Closeable的对象都可以使用try-with-resource来实现异常处理和关闭资源。
    //
    //下面通过对比来体会这个新特性。
    //JDK1.7之前：
    public static class PreJDK7 {

        public static String readFirstLingFromFile(String path) throws IOException {
            BufferedReader br = null;

            try {
                br = new BufferedReader(new FileReader(path));
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {//必须在这里关闭资源
                if (br != null)
                    br.close();
            }
            return null;
        }
    }

    //JDK1.7及以后版本

    /**
     * JDK1.7之后就可以使用try-with-resources,不需要
     * 我们在finally块中手动关闭资源
     *
     * @author Liao
     */
    public static class AboveJDK7 {

        static String readFirstLineFromFile(String path) throws IOException {

            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                return br.readLine();
            }
        }
    }

    //异常抛出顺序
    //在JDK1.7之前如果rd.readLine()与rd.close()都抛出异常则只会抛出finally块中的异常，不会抛出rd.readLine()中的异常，这样经常会导致得到的异常信息不是调用程序想要得到的。
    //
    //在JDK1.7及以后采用了try-with-resource机制，如果在try-with-resource声明中抛出异常(如文件无法打开或无法关闭)的同时rd.readLine()也抛出异常，则只会抛出rd.readLine()的异常。
    //try-with-resource可以声明多个资源(声明语句之间分号分割，最后一个可忽略分号)。下面的例子是在一个ZIP文件中检索文件名并将检索后的文件存入一个txt文件中。
    //
    //JDK1.7及以上版本：
    public static class AboveJDK7_2 {

        public static void writeToFileZipFileContents(String zipFileName, String outputFileName) throws java.io.IOException {

            java.nio.charset.Charset charset = java.nio.charset.Charset.forName("US-ASCII");

            java.nio.file.Path outputFilePath = java.nio.file.Paths.get(outputFileName);

            //打开zip文件，创建输出流
            try (
                    java.util.zip.ZipFile zf = new java.util.zip.ZipFile(zipFileName);

                    java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(outputFilePath, charset)
            ) {//遍历文件写入txt
                for (java.util.Enumeration entries = zf.entries(); entries.hasMoreElements(); ) {

                    String newLine = System.getProperty("line.separator");

                    String zipEntryName = ((java.util.zip.ZipEntry) entries.nextElement()).getName() + newLine;
                    writer.write(zipEntryName, 0, zipEntryName.length());
                }
            }
        }
    }
}
