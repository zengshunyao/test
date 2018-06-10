package cn.ccsgroup.ccsframework.common.task.service.impl;

import cn.ccsgroup.ccsframework.common.bean.BeanConverter;
import cn.ccsgroup.ccsframework.common.task.dao.ScheduleJobDao;
import cn.ccsgroup.ccsframework.common.task.entity.ScheduleJob;
import cn.ccsgroup.ccsframework.common.task.service.ScheduleJobService;
import cn.ccsgroup.ccsframework.common.task.utils.ScheduleUtils;
import cn.ccsgroup.ccsframework.common.task.vo.ScheduleJobVo;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 定时任务
 * <p/>
 * Created by shunyao.zeng on 12/19/14.
 */
//@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

    /**
     * 调度工厂Bean
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * 通用dao
     */
    @Autowired
    private ScheduleJobDao scheduleJobDao;

    /**
     * 初始化定时任务
     *
     * @return Date
     */
    @Override
    public void initScheduleJob() {
        Map param = null;
        List<ScheduleJob> scheduleJobList = scheduleJobDao.queryScheduleJobList(param);
        if (CollectionUtils.isEmpty(scheduleJobList)) {
            return;
        }
        for (ScheduleJob scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobName(),
                    scheduleJob.getJobGroup());

            //不存在，创建一个
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                //已存在，那么更新相应的定时设置
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
        return;
    }

    /**
     * 新增
     *
     * @param scheduleJobVo
     * @return
     */
    @Override
    public Long insertScheduleJob(ScheduleJobVo scheduleJobVo) {
        ScheduleJob scheduleJob = scheduleJobVo.getTargetObject(ScheduleJob.class);
        //创建调度
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        //创建记录写入数据库
        return scheduleJobDao.insertScheduleJob(scheduleJob);
    }

    /**
     * 直接修改 只能修改运行的时间，参数、同异步等无法修改
     *
     * @param scheduleJobVo
     */
    @Override
    public int updateScheduleJob(ScheduleJobVo scheduleJobVo) {
        ScheduleJob scheduleJob = scheduleJobVo.getTargetObject(ScheduleJob.class);
        //修改调度
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        //修改数据库对应记录
        return scheduleJobDao.updateScheduleJobByPrimaryKey(scheduleJob);
    }

    /**
     * 删除重新创建方式
     *
     * @param scheduleJobVo
     */
    @Override
    public int delUpdateScheduleJob(ScheduleJobVo scheduleJobVo) {
        //1.获得调度
        ScheduleJob scheduleJob = scheduleJobVo.getTargetObject(ScheduleJob.class);
        //2.先删除调度
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
        //3.再创建调度
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        //4.数据库直接更新记录信息即可
        return scheduleJobDao.updateScheduleJobByPrimaryKey(scheduleJob);
    }

    /**
     * 删除
     *
     * @param scheduleJobId
     */
    @Override
    public int deleteScheduleJob(Long scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobDao.getScheduleJobByPrimaryKey(scheduleJobId);
        //删除运行的任务
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
        //删除数据库数据
        return scheduleJobDao.deleteScheduleJobByPrimaryKey(scheduleJobId);
    }

    /**
     * 运行一次
     *
     * @param scheduleJobId the schedule job id
     */
    @Override
    public void runOnce(Long scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobDao.getScheduleJobByPrimaryKey(scheduleJobId);
        ScheduleUtils.runOnce(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

    /**
     * 暂停任务
     *
     * @param scheduleJobId the schedule job id
     */
    @Override
    public void pauseJob(Long scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobDao.getScheduleJobByPrimaryKey(scheduleJobId);
        ScheduleUtils.pauseJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
        //演示数据库就不更新了

    }

    /**
     * 恢复任务
     *
     * @param scheduleJobId the schedule job id
     */
    @Override
    public void resumeJob(Long scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobDao.getScheduleJobByPrimaryKey(scheduleJobId);
        ScheduleUtils.resumeJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());

        //演示数据库就不更新了
    }

    /**
     * 获取任务对象
     *
     * @param scheduleJobId
     * @return
     */
    @Override
    public ScheduleJobVo getScheduleJob(Long scheduleJobId) {
        ScheduleJob scheduleJob = scheduleJobDao.getScheduleJobByPrimaryKey(scheduleJobId);
        return scheduleJob.getTargetObject(ScheduleJobVo.class);
    }

    /**
     * 查询所有任务列表
     *
     * @param scheduleJobVo
     * @return
     */
    @Override
    public List<ScheduleJobVo> queryScheduleJobList(ScheduleJobVo scheduleJobVo) {
        Map param = null;
        List<ScheduleJob> scheduleJobs = scheduleJobDao.queryScheduleJobList(param);

        List<ScheduleJobVo> scheduleJobVoList = BeanConverter.convert(
                ScheduleJobVo.class, scheduleJobs);

        try {
            for (ScheduleJobVo vo : scheduleJobVoList) {
                JobKey jobKey = ScheduleUtils.getJobKey(vo.getJobName(), vo.getJobGroup());
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                if (CollectionUtils.isEmpty(triggers)) {
                    continue;
                }

                // 这里一个任务可以有多个触发器， 但是我们一个任务对应一个触发器，
                // 所以只取第一个即可，清晰明了
                Trigger trigger = triggers.iterator().next();
                scheduleJobVo.setJobTrigger(trigger.getKey().getName());

                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                vo.setStatus(triggerState.name());

                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    vo.setCronExpression(cronExpression);
                } else if (trigger instanceof DailyTimeIntervalTrigger) {
                    System.out.println("DailyTimeIntervalTrigger");
                } else if (trigger instanceof CalendarIntervalTrigger) {
                    System.out.println("CalendarIntervalTrigger");
                } else if (trigger instanceof SimpleTrigger) {
                    System.out.println("SimpleTrigger");
                } else {
                    System.out.println("null");
                }
            }
        } catch (SchedulerException e) {
            //演示用，就不处理了
        }
        return scheduleJobVoList;
    }

    /**
     * 查询正在运行的任务
     *
     * @return
     */
    @Override
    public List<ScheduleJobVo> queryExecutingJobList() {
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            List<ScheduleJobVo> jobList = new LinkedList<ScheduleJobVo>();//new ArrayList<ScheduleJobVo>executingJobs.size()

            for (JobExecutionContext executingJob : executingJobs) {
                ScheduleJobVo job = new ScheduleJobVo();
                //任务描述
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();//任务标识
                Trigger trigger = executingJob.getTrigger();//调度策略

                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setJobTrigger(trigger.getKey().getName());

                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
            return jobList;
        } catch (SchedulerException e) {
            //演示用，就不处理了
            return null;
        }
    }
}
