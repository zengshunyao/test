package cn.ccsgroup.ccsframework.common.task.entity;

import cn.ccsgroup.ccsframework.common.task.entity.base.AbstractBaseBean;

/**
 * 计划任务信息
 */
public class ScheduleJob extends AbstractBaseBean {

    private String jobName;


    private String aliasName;


    private String jobGroup;


    private String jobTrigger;


    private String status;


    private String cronExpression;


    private Integer isSync;


    private String description;


    private String systemName;


    private String className;


    private String methodName;

    public ScheduleJob() {
        super();
    }


    public String getJobName() {
        return jobName;
    }


    public void setJobName(String jobName) {
        this.jobName = jobName;
    }


    public String getAliasName() {
        return aliasName;
    }


    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }


    public String getJobGroup() {
        return jobGroup;
    }


    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }


    public String getJobTrigger() {
        return jobTrigger;
    }


    public void setJobTrigger(String jobTrigger) {
        this.jobTrigger = jobTrigger;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getCronExpression() {
        return cronExpression;
    }


    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }


    public Integer getIsSync() {
        return isSync;
    }


    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getSystemName() {
        return systemName;
    }


    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }


    public String getClassName() {
        return className;
    }


    public void setClassName(String className) {
        this.className = className;
    }


    public String getMethodName() {
        return methodName;
    }


    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

}