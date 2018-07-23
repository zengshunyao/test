package com.funi.distributedcomputer.zookeeper.distrubutelock.javaapi;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

public class LockWatch implements Watcher {
    private CountDownLatch countDownLatch;


    public LockWatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType().equals(Event.EventType.NodeDeleted)) {
            countDownLatch.countDown();
        }
    }
}
