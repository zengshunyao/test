package com.task.quartz;

import org.quartz.*;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(定时任务运行工厂类)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 * <p/>
 * 为了避免并发时, 存储数据造成混乱
 * 禁止并发执行多个相同定义的JobDetail, 这个注解是加在Job类上的,
 * 但意思并不是不能同时执行多个Job, 而是不能并发执行同一个Job Definition(由JobDetail定义),
 * 但是可以同时执行多个不同的JobDetail
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {

    private static final String ScheduleJob = "scheduleJob";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("任务成功运行");
        ScheduleJob scheduleJob = null;
        if (context.getMergedJobDataMap().get(ScheduleJob) instanceof ScheduleJob) {
            scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJob);
        }
        if (scheduleJob != null) {
            System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]");
        }
    }
}