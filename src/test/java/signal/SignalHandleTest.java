package signal;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(描述该文件做什么)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class SignalHandleTest implements SignalHandler {

    private static boolean isContinue = true;

    public static void main(String[] args) {
        SignalHandler testSignalHandler = new SignalHandleTest();
        // install signals
        Signal sig = new Signal("Foxmail.exe");
        Signal.handle(sig, testSignalHandler);

        int count = 0;
        while (isContinue) {
            try {
                System.out.println(count);
                count++;
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Closed");
    }

    @Override
    public void handle(Signal arg0) {
        System.out.println(arg0.getName() + "is recevied.");
        isContinue = false;
    }
}