package category.book.javabingfabianchengyishu.ch08.one;

import java.util.Arrays;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/18 14:29
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */

public class ExchangerTest {

    private static final Exchanger<String> exgr = new Exchanger<String>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        Arrays.asList("a,b,c".split(","));

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水A"; // A录入银行流水数据
                    String bValue = exgr.exchange(A);
                    System.out.println(bValue);
                } catch (InterruptedException e) {
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String b = "银行流水B"; // B录入银行流水数据
                    String aValue = exgr.exchange(b);

                    System.out.println(aValue);
                    System.out.println("A和B数据是否一致：" + aValue.equals(b)
                            + "，A录入的是：" + aValue + "，B录入是：" + b);
                } catch (InterruptedException e) {
                }
            }
        });

        threadPool.shutdown();

        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
