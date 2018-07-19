package com.funi.distributedcomputer.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorCreateSessionDemo {

    private static final String CONNECTSTRING =
            "192.168.137.101:2181"
                    + ",192.168.137.102:2181"
                    + ",192.168.137.103:2181"
                    + ",192.168.137.104:2181,";

    public static void main(String[] args) {
        //创建2种会话方式 normal
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECTSTRING, 5000,
                5000, new ExponentialBackoffRetry(1000, 3));
        curatorFramework.start();

        //fluent风格
        CuratorFramework curatorFramework2 = CuratorFrameworkFactory.builder().connectString(CONNECTSTRING).sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace("zsy").build();

        System.out.println(true);
    }
}