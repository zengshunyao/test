package com.funi.distributedcomputer.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class CuratorEventDemo {

    /**
     * 三种watcher来做节点的监听
     * pathcache   监视一个路径下子节点的创建、删除、节点数据更新
     * NodeCache   监视一个节点的创建、更新、删除
     * TreeCache   pathcaceh+nodecache 的合体（监视路径下的创建、更新、删除事件），
     * 缓存路径下的所有子节点的数据
     */

    public static void main(String[] args) throws Exception {
        CuratorFramework curatorFramework = CuratorClientUtils.getInstance();

        /**
         * 节点变化NodeCache
         */
//        NodeCache cache=new NodeCache(curatorFramework,"/curator",false);
//        cache.start(true);
//
//        cache.getListenable().addListener(()-> System.out.println("节点数据发生变化,变化后的结果" +
//                "："+new String(cache.getCurrentData().getData())));
//
//        curatorFramework.setData().forPath("/curator","菲菲".getBytes());

//        NodeCache cache = new NodeCache(curatorFramework, "/test", false);
//        cache.start();
//        cache.getListenable().addListener(() ->
//                System.out.println("节点数据发生变化，变化后的结果为：" + new String(cache.getCurrentData().getData()))
//        );
//        //curatorFramework.create().forPath("/test","qazwsx1".getBytes());
//        curatorFramework.setData().forPath("/test", "qazwsx".getBytes());
//        System.in.read();

        /**
         * PatchChildrenCache
         */
/*
        PathChildrenCache cache=new PathChildrenCache(curatorFramework,"/event",true);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        // Normal / BUILD_INITIAL_CACHE /POST_INITIALIZED_EVENT

        cache.getListenable().addListener((curatorFramework1,pathChildrenCacheEvent)->{
            switch (pathChildrenCacheEvent.getType()){
                case CHILD_ADDED:
                    System.out.println("增加子节点");
                    break;
                case CHILD_REMOVED:
                    System.out.println("删除子节点");
                    break;
                case CHILD_UPDATED:
                    System.out.println("更新子节点");
                    break;
                default:break;
            }
        });

        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/event","event".getBytes());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("1");
        curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/event/event1","1".getBytes());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("2");

        curatorFramework.setData().forPath("/event/event1","222".getBytes());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("3");

        curatorFramework.delete().forPath("/event/event1");
        System.out.println("4");

        System.in.read();



        /**
        * PathChildrenCache
        * */

//        curatorFramework.create()
//                .withMode(CreateMode.PERSISTENT).forPath("/event", "qazwsxc".getBytes());
//        TimeUnit.SECONDS.sleep(1);
//        System.out.println(1);

        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, "/event", false);
        //StartMode  NORMAL 、BUILD_INITIAL_CACHE、POST_INITIALIZED_EVENT
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);

        pathChildrenCache.getListenable().addListener((curatorFramework1, pathChildrenCacheEvent) -> {
            switch (pathChildrenCacheEvent.getType()) {
                case CHILD_ADDED:
                    System.out.println("创建子节点");
                    break;
                case CHILD_UPDATED:
                    System.out.println("更新子节点");
                    break;

                case CHILD_REMOVED:
                    System.out.println("删除子节点");
                    break;

                case CONNECTION_LOST:
                    System.out.println("CONNECTION_LOST");
                    break;

                case INITIALIZED:
                    System.out.println("INITIALIZED");
                    break;

                case CONNECTION_SUSPENDED:
                    System.out.println("CONNECTION_SUSPENDED");
                    break;
                case CONNECTION_RECONNECTED:
                    System.out.println("CONNECTION_RECONNECTED");
                    break;

            }
        });

        //创建节点
        curatorFramework.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT).forPath("/event/event-1", "1123445".getBytes());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(2);
        //更新节点
        curatorFramework.setData().forPath("/event/event-1", "1123445".getBytes());
        TimeUnit.SECONDS.sleep(1);
        System.out.println(3);
        //删除节点
        curatorFramework.delete().forPath("/event/event-1");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(4);



        System.in.read();
    }
}
