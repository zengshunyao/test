package com.funi.distributedcomputer.zookeeper.distrubutelock.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**********************************************************************
 * &lt;p&gt;文件名：${FILE_NAME} &lt;/p&gt;
 * &lt;p&gt;文件描述：${DESCRIPTION}(描述该文件做什么)
 * @project_name：test
 * @author zengshunyao
 * @date 2018/7/23 15:52
 * @history
 * @department：政务事业部
 * Copyright ChengDu Funi Cloud Code Technology Development CO.,LTD 2014
 *                    All Rights Reserved.
 */
public class MasterSelectTest {

    private final static String CONNECTSTRING = "47.52.33.73:2181";

    public static void main(String[] args) throws InterruptedException {
        List<MasterSelector> masterSelectors = new LinkedList<MasterSelector>();
        try {

            int num = 10;
            CountDownLatch countDownLatch = new CountDownLatch(num);
            for (int i = 0; i < num; i++) {

                ZkClient zkClient = new ZkClient(CONNECTSTRING, 5000, 5000,
                        new SerializableSerializer());

                UserCenter userCenter = new UserCenter();
                userCenter.setMc_id(i + 1);
                userCenter.setMc_name("客户端" + (i + 1));


                final MasterSelector masterSelector = new MasterSelector(userCenter, zkClient);
                masterSelectors.add(masterSelector);

                new Thread(() -> {
                    countDownLatch.countDown();

                    System.out.println(userCenter.getMc_name() + "->启动中.....");
                    masterSelector.start();
                }).start();

                TimeUnit.SECONDS.sleep(1);
            }
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TimeUnit.SECONDS.sleep(10);
            masterSelectors.forEach((masterSelector -> {
                masterSelector.stop();
            }));
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
