package com.funi.distributedcomputer.zookeeper.distrubutelock.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

public class MasterSelector {

    private static final String CONNECTSTRING =
            "192.168.137.101:2181"
                    + ",192.168.137.102:2181"
                    + ",192.168.137.103:2181"
                    + ",192.168.137.104:2181,";

    private final static String LEADER = "/LEADER";

    public static void main(String[] args) {
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder().connectString(CONNECTSTRING)
                        .connectionTimeoutMs(5000).sessionTimeoutMs(5000)
                        .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

        LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, LEADER, new LeaderSelectorListenerAdapter() {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println("我进来了........");
                TimeUnit.SECONDS.sleep(2);
            }
        });

        leaderSelector.autoRequeue();
        leaderSelector.start();
    }
}
