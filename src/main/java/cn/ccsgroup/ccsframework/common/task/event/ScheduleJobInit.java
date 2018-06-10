package cn.ccsgroup.ccsframework.common.task.event;

import cn.ccsgroup.ccsframework.common.task.service.ScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 定时任务初始化
 * <p/>
 * Created by shunyao.zeng on 12/19/14.
 */
@Component
public class ScheduleJobInit {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleJobInit.class);

    /**
     * 定时任务service
     */
    @Autowired
    private ScheduleJobService scheduleJobService;

    public ScheduleJobInit() {
        super();
    }

    /**
     * 项目启动时初始化
     * 实现初始化之后
     */
    @PostConstruct
    public void init() {

        if (LOG.isInfoEnabled()) {
            LOG.info("init");
        }

        scheduleJobService.initScheduleJob();

        if (LOG.isInfoEnabled()) {
            LOG.info("end");
        }
    }
}
