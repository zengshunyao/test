package com.funi.distributedcomputer.kafka;

import kafka.utils.ShutdownableThread;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/8/20 10:11
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class MyConsumer extends ShutdownableThread {
    public MyConsumer(String name, boolean isInterruptible) {
        super(name, isInterruptible);
    }

    @Override
    public void doWork() {

    }
}
