package com.task.quartz;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.*;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(定时服务)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
//@Component("quartzService")
public class QuartzService {

    private static final String ScheduleJob = "scheduleJob";

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     * 计划任务map
     */
    private static Map<String, ScheduleJob> jobMap = new HashMap<String, ScheduleJob>();

    static {
        /**
         * 接下来我们创建测试数据，实际应用中该数据可以保存在数据库等地方，
         * 我们把任务的分组名+任务名作为任务的唯一key，和quartz中的实现方式一致：
         */
        for (int i = 0; i < 5; i++) {
            ScheduleJob job = new ScheduleJob();
            job.setJobId("10001" + i);
            job.setJobName("data_import" + i);
            job.setJobGroup("dataWork");
            job.setJobStatus("1");
//            job.setCronExpression("0/5 * * * * ?");
            job.setCronExpression("0/" + (5 + i) + " * * * * ?");
            job.setDesc("数据导入任务");
            addJob(job);
        }

    }

    public QuartzService() {
        super();
    }

    /**
     * 添加任务
     *
     * @param scheduleJob
     */
    public static void addJob(ScheduleJob scheduleJob) {
        jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(), scheduleJob);
    }

    /**
     * 获得所有的任务
     *
     * @return
     */
    public static List<ScheduleJob> getAllJob() {
        List<ScheduleJob> list = new LinkedList<ScheduleJob>();
        for (Map.Entry<String, ScheduleJob> entry : jobMap.entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    /**
     * 有了调度工厂，有了任务运行入口实现类，有了任务信息，接下来就是创建我们的定时任务了，
     * 在这里我把它设计成一个Job对应一个trigger，两者的分组及名称相同，方便管理，
     * 条理也比较清晰，在创建任务时如果不存在新建一个，如果已经存在则更新任务，主要代码如下：
     *
     * @throws org.quartz.SchedulerException
     */
    public void init() throws SchedulerException {
        //schedulerFactoryBean 由spring创建注入
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        //这里获取任务信息数据list
        List<ScheduleJob> jobList = QuartzService.getAllJob();

        for (ScheduleJob job : jobList) {
            //调度策略标识 表示trigger的身份
            TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
            //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //不存在，创建一个
            if (null == trigger) {
                //任务信息
                JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                        .withIdentity(job.getJobName(), job.getJobGroup()).build();
                //设置参数
                jobDetail.getJobDataMap().put(ScheduleJob, job);
                //满足表达式的定时器(调度)
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression()/*时间表达式*/);
                //按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger()
                        .withIdentity(job.getJobName(), job.getJobGroup())/*定义身份*/
                        .withSchedule(scheduleBuilder/*时间表达式*/).build();
                //注册调度
                scheduler.scheduleJob(jobDetail/*任务*/, trigger/*调度策略*/);
            } else {
                // Trigger已存在，那么更新相应的定时设置
                //表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
                        .getCronExpression());
                //按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                        .withSchedule(scheduleBuilder).build();
                //按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }
    }

    /**
     * 计划中的任务
     *
     * @return
     */
    public List<ScheduleJob> queryPlanIngList() throws SchedulerException {
        //schedulerFactoryBean 由spring创建注入
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        //定时任务唯一标识集合
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<ScheduleJob> jobList = new LinkedList<ScheduleJob>();

        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);

            for (Trigger trigger : triggers) {
                ScheduleJob job = new ScheduleJob();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setDesc("触发器:" + trigger.getKey());

                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setJobStatus(triggerState.name());

                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

    /**
     *
     */
    public void destroy() {
        try {
            if (schedulerFactoryBean != null) {
                schedulerFactoryBean.destroy();
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        jobMap.clear();
    }
}
