package com.task;

import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

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
//@Component("taskJob")
public class DataConversionTask {
    private final static AtomicInteger count = new AtomicInteger();

    public DataConversionTask() {
        super();
        System.out.println(count.incrementAndGet());
    }

    /**
     * 日志对象
     */
    private static final Logger LOG = Logger.getLogger(DataConversionTask.class);

    public void execute() {
        if (LOG.isInfoEnabled()) {
            //LOG.info("数据转换任务线程开始执行");
        }
        System.out.println("--------------execute-------------");
//        try {
//            dataWorkContext.run();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
    }
}