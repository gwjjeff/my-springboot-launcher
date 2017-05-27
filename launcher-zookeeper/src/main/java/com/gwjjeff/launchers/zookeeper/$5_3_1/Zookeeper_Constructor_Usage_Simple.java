package com.gwjjeff.launchers.zookeeper.$5_3_1;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jeff on 2017/5/17.
 */
@Slf4j
public class Zookeeper_Constructor_Usage_Simple implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void run() throws Exception {
        ZooKeeper zookeeper = new ZooKeeper(
                "localhost:2181",
                5000,
                new Zookeeper_Constructor_Usage_Simple()
        );
        log.info("state: {}", zookeeper.getState());

        try {
            connectedSemaphore.await();
        } catch (InterruptedException e) { }

        log.info("ZooKeeper session established.");
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("Receive watched event: {}", watchedEvent);

        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            connectedSemaphore.countDown();
        }
    }
}
