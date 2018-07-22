package com.funi.distributedcomputer.zookeeper.distrubutelock.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperClient {
    private static final String CONNECTSTRING =
            "192.168.137.101:2181"
                    + ",192.168.137.102:2181"
                    + ",192.168.137.103:2181"
                    + ",192.168.137.104:2181,";

    private static int sessionTimeout = 5000;

//    public static ZooKeeper getInstance() {
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        ZooKeeper zooKeeper = null;
//        try {
//            zooKeeper = new ZooKeeper(CONNECTSTRING, sessionTimeout, new Watcher() {
//                @Override
//                public void process(WatchedEvent event) {
//                    if (event.getState() == Event.KeeperState.SyncConnected) {
//                        countDownLatch.countDown();
//                    }
//                }
//            });
//
//            countDownLatch.await();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return zooKeeper;
//    }

    public static int getSessionTimeout() {
        return sessionTimeout;
    }

    //获取连接
    public static ZooKeeper getInstance() throws IOException, InterruptedException {
        final CountDownLatch connectStatus = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(CONNECTSTRING, sessionTimeout, new Watcher() {
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    connectStatus.countDown();
                }
            }
        });
        connectStatus.await();
        return zooKeeper;
    }

//    public static int getSessionTimeout() {
//        return sessionTimeout;
//    }

}
