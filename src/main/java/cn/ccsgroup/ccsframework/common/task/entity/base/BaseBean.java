package cn.ccsgroup.ccsframework.common.task.entity.base;

import cn.ccsgroup.ccsframework.common.pager.Pageable;

import java.io.Serializable;

/**
 * Created by ZJD on 2016/4/7.
 */
public class BaseBean extends Pageable implements Serializable ,Cloneable {

        //表的id序列管理..
        protected Integer id;

        protected String createTime;
        protected String updateTime;
        protected String deleteTime;
        protected String creatorId;
        protected String updaterId;
        protected String deleterId;

    public BaseBean() {
    }

    public BaseBean(Integer id, String createTime, String updateTime, String deleteTime, String creatorId, String updaterId, String deleterId) {
        this.id = id;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleteTime = deleteTime;
        this.creatorId = creatorId;
        this.updaterId = updaterId;
        this.deleterId = deleterId;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getDeleteTime() {
        return deleteTime;
    }
    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }
    public String getDeleterId() {
        return deleterId;
    }

    public void setDeleterId(String deleterId) {
        this.deleterId = deleterId;
    }
}
