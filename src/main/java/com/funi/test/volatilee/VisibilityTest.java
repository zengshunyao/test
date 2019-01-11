package com.funi.test.volatilee;

/**********************************************************************   
 * &lt;p&gt;文件名：VisibilityTest.java &lt;/p&gt;
 * &lt;p&gt;文件描述：(描述该文件做什么) 
 * @project_name：test
 * @author zengshunyao
 * @create 2018/12/29 21:53 
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014 
 *                    All Rights Reserved.
 */
public class VisibilityTest {
    public static void main(String[] args) throws Exception {
        VisibilityThread v = new VisibilityThread();
        v.start();

        Thread.sleep(100);//停顿1秒等待新启线程执行
        System.out.println("即将置stop值为true");
        v.stopIt();
        Thread.sleep(100);
        System.out.println("finish main");
        System.out.println("main中通过getStop获取的stop值:" + v.getStop());
    }
}
