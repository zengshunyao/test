package com.funi.distributedcomputer.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;

public class SessionDemo {

    private static final String CONNECTSTRING =
            "192.168.137.101:2181"
                    + ",192.168.137.102:2181"
                    + ",192.168.137.103:2181"
                    + ",192.168.137.104:2181,";

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(CONNECTSTRING, 4000);
        System.out.println(zkClient);
    }
}
