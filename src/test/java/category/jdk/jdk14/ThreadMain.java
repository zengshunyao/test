package category.jdk.jdk14;

/**********************************************************************   
 * &lt;p&gt;文件名：ThreadMain.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2019/4/9 23:12 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class ThreadMain {

    public static void main(String[] args) throws InterruptedException {

        Runnable run = new SubThread();
        Thread thread = new Thread(run, "sub");

        thread.start();
        // thread.join();

        System.out.println(String.format("Thread:%s", Thread.currentThread().getName()));
        System.out.println(SubThread.class.cast(run).complated);
    }

    static class SubThread implements Runnable {


        private boolean complated = false;

        @Override
        public void run() {
            System.out.println(String.format("Thread:%s", Thread.currentThread().getName()));
            this.complated = true;
        }
    }
}
