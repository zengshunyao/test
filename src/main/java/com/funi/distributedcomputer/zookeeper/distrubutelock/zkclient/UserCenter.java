package com.funi.distributedcomputer.zookeeper.distrubutelock.zkclient;

import java.io.Serializable;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/7/23 14:10
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class UserCenter implements Serializable {


    private static final long serialVersionUID = 2904151014193367721L;

    /**
     * 机器id
     */
    private int mc_id;

    /**
     * 机器名称
     */
    private String mc_name;

    public int getMc_id() {
        return mc_id;
    }

    public void setMc_id(int mc_id) {
        this.mc_id = mc_id;
    }

    public String getMc_name() {
        return mc_name;
    }

    public void setMc_name(String mc_name) {
        this.mc_name = mc_name;
    }
}
