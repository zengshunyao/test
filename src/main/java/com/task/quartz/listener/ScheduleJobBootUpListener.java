package com.task.quartz.listener;

import com.task.quartz.QuartzService;
import com.util.spring.ApplicationContextHolder;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(定时任务启动)
 *
 * @author ${user}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public class ScheduleJobBootUpListener implements ServletContextListener {
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleJobBootUpListener.class);

    private QuartzService quartzService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContext webApplicationContext = ApplicationContextHolder.getWebApplicationContext(sce);
        if (webApplicationContext == null) {
            LOG.error("webApplicationContext 为null");
        }
        quartzService = webApplicationContext.getBean("quartzService", QuartzService.class);
        if (quartzService == null) {
            LOG.error("quartzService 为null");
        }

        try {
            quartzService.init();
            LOG.error("quartzService 初始化成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
            LOG.error("quartzService 初始化报错");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (quartzService != null) {
            quartzService.destroy();
            LOG.error("quartzService 销毁成功");
        }
    }
}
