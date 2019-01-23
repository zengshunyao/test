package cn.ccsgroup.ccsframework.common.task.dao;

import cn.ccsgroup.ccsframework.common.task.entity.ScheduleJob;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
@Repository
public class ScheduleJobDao {
    public List<ScheduleJob> queryScheduleJobList(Map param) {
        return null;
    }

    public Long insertScheduleJob(ScheduleJob scheduleJob) {
        return null;
    }

    public int updateScheduleJobByPrimaryKey(ScheduleJob scheduleJob) {
        return 0;
    }

    public ScheduleJob getScheduleJobByPrimaryKey(Long scheduleJobId) {
        return null;
    }

    public int deleteScheduleJobByPrimaryKey(Long scheduleJobId) {
        return 0;
    }
}
