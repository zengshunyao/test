package javaBase.memory;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(栈层级不足探究)
 *
 * @author ${user}
 * @VM args:-Xss128k
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class StackOverFlow {
    private int i;

    public void plus() {
        i++;
        plus();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        StackOverFlow stackOverFlow = new StackOverFlow();
        try {
            stackOverFlow.plus();
        } catch (Exception e) {
            System.out.println("Exception:stack length:" + stackOverFlow.i);
            e.printStackTrace();
        } catch (Error e) {
            System.out.println("Error:stack length:" + stackOverFlow.i);
            e.printStackTrace();
        }
    }
}
