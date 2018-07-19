package com.funi.distributedcomputer.zookeeper.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ClientSessionDemo implements Watcher {
    private static final String CONNECT_URI =
            "192.168.137.101:2181"
                    + ",192.168.137.102:2181"
                    + ",192.168.137.103:2181"
                    + ",192.168.137.104:2181,";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) {
        try {
            zooKeeper = new ZooKeeper(CONNECT_URI, 5000, new ClientSessionDemo());

            countDownLatch.await();
            System.out.println("我等watch输出后" + zooKeeper.getState());

            System.out.println(new Date().toString() + "=====开始创建======================");
            String result = zooKeeper.create("/node3", "qazwsx".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("创建成功：" + result);
            System.out.println(new Date().toString() + "=====完毕创建======================");
            Thread.sleep(2000);

            System.out.println(new Date().toString() + "=====开始读取======================");
            zooKeeper.getData("/node3", new ClientSessionDemo(), stat);
            System.out.println(new Date().toString() + "=====结束读取======================");
            Thread.sleep(2000);

            System.out.println(new Date().toString() + "=====开始修改======================");
            zooKeeper.setData("/node3", "dfgfdgfdg".getBytes(), -1);
            zooKeeper.setData("/node3", "dfgfdgfdg".getBytes(), -1);
            System.out.println("修改成功:");
            System.out.println(new Date().toString() + "=====结束修改======================");

            TimeUnit.SECONDS.sleep(2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } finally {
            try {
                zooKeeper.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(new Date().toString() + "-------------" + watchedEvent.getState());
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {


            if (watchedEvent.getType() == Event.EventType.None
                    && null == watchedEvent.getPath()) {
//                System.out.println(new Date().toString() + watchedEvent.getState().toString());
                System.out.println(new Date().toString() + watchedEvent.getType().toString());
                System.out.println("-------SyncConnected over--------");
                countDownLatch.countDown();
            } else if (watchedEvent.getType().equals(Event.EventType.NodeDataChanged)) {
                try {
                    System.out.println(new Date().toString() + "路径：" + watchedEvent.getPath()
                            + "->改变后的值:"
                            + new String(zooKeeper.getData(watchedEvent.getPath(), true, stat)).toString());
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
                System.out.println("dfgffffffffffff");
            } else if (watchedEvent.getType() == Event.EventType.NodeCreated) {
                try {
                    System.out.println(new Date().toString() + "路径：" + watchedEvent.getPath()
                            + "->值:"
                            + new String(zooKeeper.getData(watchedEvent.getPath(), true, stat)));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (watchedEvent.getType() == Event.EventType.NodeDeleted) {

            }
            System.out.println(new Date().toString() + "------其实-------" + watchedEvent.getState());
        }


    }
}
