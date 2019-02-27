package category.book.javabingfabianchengyishu.ch08.one.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：(流量控制)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/4/18 14:12
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class SemaphoreTest2 {
    //    假若一个工厂有5台机器，但是有8个工人，一台机器同时只能被一个工人使用，
    //    只有某个人使用完了，其他工人才能继续使用。那么我们就可以通过Semaphore来实现

    static Random random = new Random();

    public static void main(String[] args) {
        int PERSON_N = 8;//工人数
        int MACHINE_N = 5;//机器数目

        //机器
        Semaphore semaphore = new Semaphore(MACHINE_N);

        for (int i = 0; i < PERSON_N; i++)
            new Worker(i + 1, semaphore).start();
    }

    static class Worker extends Thread {
        private int gonghao;
        private Semaphore semaphore;

        public Worker(int gonghao, Semaphore semaphore) {
            this.gonghao = gonghao;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(this.gonghao + "号工人" + "占用一个机器在生产...");
                Thread.sleep(random.nextInt(2000));
                System.err.println(this.gonghao + "号工人" + "释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
