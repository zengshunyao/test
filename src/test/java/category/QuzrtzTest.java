package category;

import org.quartz.JobDetail;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

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
public class QuzrtzTest {
    public static void main(String[] args) {
//        JobDetail jobDetail = initJobDetail();
//        CronTriggerFactoryBean trigger = initTrigger(jobDetail);
//        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
//        schedulerFactoryBean.setTriggers(trigger);
    }

    public static MethodInvokingJobDetailFactoryBean initJobDetail() {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setGroup("job_work");
        jobDetail.setName("job_work_name");
        jobDetail.setConcurrent(false);
        jobDetail.setTargetObject(new TestJob());
        jobDetail.setTargetMethod("category");
        return jobDetail;
    }

    public static CronTriggerFactoryBean initTrigger(JobDetail jobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setGroup("work_default_name");
        trigger.setName("work_default");
        trigger.setJobDetail(jobDetail);
        trigger.setCronExpression("0/5 * * * * ?");
        return trigger;
    }

    public static class TestJob {
        public void test() {
            System.out.println(System.currentTimeMillis());
        }
    }
}
