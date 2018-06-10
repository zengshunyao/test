package cn.ccsgroup.ccsframework.common.task.quartz;

import cn.ccsgroup.ccsframework.common.task.entity.ScheduleJob;
import cn.ccsgroup.ccsframework.common.task.vo.ScheduleJobVo;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 同步的任务工厂类
 * <p/>
 * Created by shunyao.zeng on 12/19/14.
 */
public class JobSyncFactory implements Job {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(JobSyncFactory.class);

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LOG.info("JobSyncFactory execute");

        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        ScheduleJob scheduleJob = (ScheduleJob) mergedJobDataMap.get(ScheduleJobVo.JOB_PARAM_KEY);

        System.out.println("jobName:" + scheduleJob.getJobName() + "  " + scheduleJob);

        System.out.println("JobSyncFactory .Successs.....");
        try {
            Thread.sleep(1000);//睡一秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
