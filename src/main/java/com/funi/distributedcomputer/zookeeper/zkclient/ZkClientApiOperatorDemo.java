package com.funi.distributedcomputer.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZkClientApiOperatorDemo {

    private static final String CONNECTSTRING =
            "192.168.137.101:2181"
                    + ",192.168.137.102:2181"
                    + ",192.168.137.103:2181"
                    + ",192.168.137.104:2181,";

    public static ZkClient getInstance() {
        ZkClient zkClient = new ZkClient(CONNECTSTRING, 4000);
        return zkClient;
    }

    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = getInstance();

        System.out.println(zkClient);

        zkClient.createEphemeral("/node", "test".getBytes());

        //zkclient 提供递归创建父节点的功能
       /* zkClient.createPersistent("/zkclient/zkclient1/zkclient1-1/zkclient1-1-1",true);
        System.out.println("success");*/

        //删除节点
//        zkClient.deleteRecursive("/zkclient");


        //获取子节点
        List<String> list = zkClient.getChildren("/node");
        System.out.println(list);

        //watcher

        zkClient.subscribeDataChanges("/node", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("节点名称：" + s + "->节点修改后的值" + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        });

        zkClient.writeData("/node", "node");
        TimeUnit.SECONDS.sleep(2);

        zkClient.subscribeChildChanges("/node", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {

            }
        });

    }
}
