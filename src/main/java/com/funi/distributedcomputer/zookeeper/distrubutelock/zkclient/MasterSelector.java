package com.funi.distributedcomputer.zookeeper.distrubutelock.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/7/23 14:15
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class MasterSelector {

    private ZkClient zkClient;

    //需要竞争的节点
    private final static String MASTER_PATH = "/master";

    //监听节点内容变化   监听器
    private IZkDataListener dataListener;

    //其他服务器
    private UserCenter server;

    //master节点
    private UserCenter master;

    //正在选择
    private boolean isRunning = false;

    private ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);

    public MasterSelector(UserCenter server, ZkClient zkClient) {
        System.out.println(server.getMc_name() + "->初始化中...争抢master权限");
        this.server = server;

        this.zkClient = zkClient;

        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                //节点如果被删除，发起选主操作
                System.out.println(master.getMc_name() + "->触发删除master...." + dataPath);
//                selectMaster();
            }
        };
    }

    /**
     * 启动选主服务
     */
    public void start() {
        if (!isRunning) {
            isRunning = true;
            //注册事件
            zkClient.subscribeDataChanges(MASTER_PATH, dataListener);
            selectMaster();
        }
    }

    /**
     * 停止选主服务
     */
    public void stop() {
        //停止
        if (isRunning) {
            isRunning = false;
            scheduledExecutorService.shutdown();
            zkClient.unsubscribeDataChanges(MASTER_PATH, dataListener);
            System.out.println(master.getMc_name() + "外界干扰停止");
            //释放主节点
            releaseMaster();
        }
    }

    /**
     * 选主服务
     */
    private void selectMaster() {
        //开始选举
        if (!isRunning) {
            System.out.println("当前服务没有启动");
            return;
        }

        try {
            zkClient.createEphemeral(MASTER_PATH, server);
            master = server;
            System.out.println(master.getMc_name() + "->我现在是master,你们要听我的");

            //定时器
            scheduledExecutorService.schedule(() -> {
                System.out.println(master.getMc_name() + "->定时任务干掉master");
                //释放锁
                releaseMaster();
            }, 2, TimeUnit.SECONDS);
        } catch (Exception e) {
            //表示master已经存在
            UserCenter userCenter = zkClient.readData(MASTER_PATH, true);
            if (userCenter == null) {
                System.out.println("启动操作：");
                //选主 递归选主
                this.checkIsMaster();
            } else {
                master = userCenter;
            }
        }
    }

    /**
     * 释放主节点
     */
    private void releaseMaster() {
        //释放锁
        //判断当前是不是master,只有master才需要释放
        if (checkIsMaster()) {
            System.out.println(master.getMc_name() + "->被干掉....");
            //删除
            zkClient.delete(MASTER_PATH);
        }
    }

    /**
     * 检查是否是主节点
     *
     * @return
     */
    public boolean checkIsMaster() {
        //判断当前的server是不是master
        UserCenter userCenter = zkClient.readData(MASTER_PATH, true);
        if (userCenter != null
                && userCenter.getMc_name().equals(server.getMc_name())) {
            master = userCenter;
            return true;
        }
        return false;
    }
}