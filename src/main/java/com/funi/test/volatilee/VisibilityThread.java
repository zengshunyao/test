package com.funi.test.volatilee;

/**********************************************************************   
 * &lt;p&gt;文件名：VisibilityThread.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2018/12/29 21:52 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class VisibilityThread extends Thread {
    private volatile boolean stop;

    public void run() {
        int i = 0;
        System.out.println("start loop.");
        while (!getStop()) {

            i++;
        }
        System.out.println("finish loop,i=" + i);
    }

    public void stopIt() {
        stop = true;
    }

    public boolean getStop() {
        return stop;
    }
}

