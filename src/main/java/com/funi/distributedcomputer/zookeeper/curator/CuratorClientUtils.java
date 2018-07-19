package com.funi.distributedcomputer.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class CuratorClientUtils {

    private static CuratorFramework curatorFramework;
    private static final String CONNECTSTRING =
            "192.168.137.101:2181"
                    + ",192.168.137.102:2181"
                    + ",192.168.137.103:2181"
                    + ",192.168.137.104:2181,";


    public static CuratorFramework getInstance() {
        curatorFramework = CuratorFrameworkFactory.
                newClient(CONNECTSTRING, 5000, 5000,
                        new ExponentialBackoffRetry(1000, 3));
        curatorFramework.start();
        return curatorFramework;
    }
}
