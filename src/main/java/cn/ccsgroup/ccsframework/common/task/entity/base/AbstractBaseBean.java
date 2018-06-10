package cn.ccsgroup.ccsframework.common.task.entity.base;

/**
 * *******************************************************************
 * &lt;p&gt;文件名：${file_name} &lt;/p&gt;
 * &lt;p&gt;文件描述：${todo}(基础bean扩展)
 *
 * @author ${shunyao.zeng}
 * @project_name：${project_name}
 * @date ${date} ${time}
 * @history
 * @department：政务事业部 Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 * All Rights Reserved.
 */
public abstract class AbstractBaseBean extends BaseBean {
    /**
     * 删除标记
     */
    private int isDeleted;

    /**
     * 版本号 增=步长+1
     */
    private Short version;
    /**
     * 备注
     */
    private String remark;

    public AbstractBaseBean() {
        super();
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
