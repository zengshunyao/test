package com.funi.distributedcomputer.zookeeper.javaapi;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class AuthControlDemo implements Watcher {
    private static final String CONNECTSTRING =
            "192.168.137.101:2181"
                    + ",192.168.137.102:2181"
                    + ",192.168.137.103:2181"
                    + ",192.168.137.104:2181,";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static CountDownLatch countDownLatch2 = new CountDownLatch(1);

    private static ZooKeeper zookeeper;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        zookeeper = new ZooKeeper(CONNECTSTRING, 5000, new AuthControlDemo());
        countDownLatch.await();

//        ACL acl = new ACL(ZooDefs.Perms.CREATE, new Id("digest", "root:root"));
//        ACL acl2 = new ACL(ZooDefs.Perms.CREATE, new Id("ip", "192.168.137.1"));

//        List<ACL> acls = new ArrayList<>();
//        acls.add(acl);
//        acls.add(acl2);
        //zookeeper.create("/auth1", "123".getBytes(), acls, CreateMode.PERSISTENT);

//        zookeeper.addAuthInfo("digest", "root:root".getBytes());
        zookeeper.addAuthInfo("digest", "root:root".getBytes());
//        zookeeper.create("/auth1", "123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        zookeeper.create("/test", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        zookeeper.create("/auth1/auth1-1", "123".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
//        zookeeper.create("test/test-1", "value".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        ZooKeeper zooKeeper1 = new ZooKeeper(CONNECTSTRING, 5000, new AuthControlDemo());
        countDownLatch.await();
//        zooKeeper1.delete("/auth1", -1);
        zooKeeper1.delete("/test", -1);

        // acl (create /delete /admin /read/write)
        //权限模式： ip/Digest（username:password）/world/super

    }

    public void process(WatchedEvent watchedEvent) {
        //如果当前的连接状态是连接成功的，那么通过计数器去控制
        //连接状态
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            if (Event.EventType.None == watchedEvent.getType() && null == watchedEvent.getPath()) {
                countDownLatch.countDown();
                countDownLatch2.countDown();
                System.out.println(watchedEvent.getState() + "-->" + watchedEvent.getType());
            }
        }

    }
}
