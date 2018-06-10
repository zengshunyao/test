package category.book.javabingfabianchengyishu.ch08.one;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/18 10:46
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class JoinCountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {

        final Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {
            }
        });

        final Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finish");
            }
        });
        parser1.start();
        parser2.start();

        parser1.join();
        parser2.join();

        System.out.println("all parser finish");
    }
}
