package com.funi.distributedcomputer.zookeeper.distrubutelock.javaapi;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DistrubuteLock {

    private static final String ROOT_LOCK_KEY = "/LOCKS";

    private ZooKeeper zooKeeper;

    private int sessionTimeOut;

    private String lockId = null;


    private static final byte[] data = {1, 2};

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public DistrubuteLock() throws IOException, InterruptedException {
        this.zooKeeper = ZookeeperClient.getInstance();
        this.sessionTimeOut = ZookeeperClient.getSessionTimeout();
    }

    protected boolean lock() {
        try {

            lockId = zooKeeper.create(ROOT_LOCK_KEY + "/", data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + "成功创建子节点->[" + lockId + "],开始去竞争锁。。。");

            List<String> children = zooKeeper.getChildren(ROOT_LOCK_KEY, true);

            SortedSet<String> sortedSet = new TreeSet<String>();
            children.stream().forEach((one) -> {
                sortedSet.add(ROOT_LOCK_KEY + "/" + one);
            });

            //拿到最小的一个节点
            String first = sortedSet.first();
            if (lockId.equals(first)) {
                System.out.println(Thread.currentThread().getName() + "成功获得锁,lock节点为->[" + lockId + "]");
                return true;
            }
            SortedSet<String> lessThan = sortedSet.headSet(lockId);
            if (!lessThan.isEmpty()) {
                //活得比当前这个lockId节点略小的最近节点
                String prevLock = lessThan.last();
                zooKeeper.exists(prevLock, new LockWatch(countDownLatch));
                countDownLatch.await(sessionTimeOut, TimeUnit.MILLISECONDS);
                //以上代码意味着 上一个节点被删除或者会话超时就可以上位了

                System.out.println(Thread.currentThread().getName() + "成功获得锁->[" + lockId + "]");
            }
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected boolean unlock() {
        System.out.println(Thread.currentThread().getName() + "开始--->释放锁->[" + lockId + "]");
        try {
            zooKeeper.delete(lockId, -1);
            System.out.println(Thread.currentThread().getName() + "成功===>释放锁->[" + lockId + "]");
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {

        int num = 6;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        Random random = new Random(500);
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                DistrubuteLock lock = null;

                try {
                    lock = new DistrubuteLock();
                    countDownLatch.countDown();

                    countDownLatch.await();

                    //让所有线程都到达这儿
                    lock.lock();
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (lock != null) {
                        lock.unlock();
                    }
                }
            }).start();
        }
    }
}
