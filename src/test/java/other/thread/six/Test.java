package other.thread.six;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**********************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(描述该文件做什么) 
 * @project_name：${project_name}
 * @author ${user}  
 * @date ${date} ${time} 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class Test {

    public static void main(String[] args) {
        doTaskWithResultInWorker();
    }

    private static void doTaskWithResultInWorker() {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Task starts");
                Thread.sleep(new Random(1000).nextInt());
                int result = 0;
                for (int i = 0; i <= 100; i++) {
                    result += new Random(1000).nextInt();
                    //Thread.sleep(new Random(100).nextInt());
                }
                System.out.println("Task finished and return result");
                return result;
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();//调用线程

        try {
            System.out.println("Before futureTask.get()");
            System.out.println("Result:" + futureTask.get());
            System.out.println("After futureTask.get()");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
