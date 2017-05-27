package com.gwjjeff.launchers.zookeeper.$5_3_2;

import com.gwjjeff.launchers.zookeeper.$5_3_1.Zookeeper_Constructor_Usage_Simple;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jeff on 2017/5/17.
 */
@Slf4j
public class Zookeeper_Create_API_Sync_Usage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void run() throws Exception {
        ZooKeeper zookeeper = new ZooKeeper(
                "localhost:2181",
                5000,
                new Zookeeper_Create_API_Sync_Usage()
        );
        log.info("state: {}", zookeeper.getState());

        connectedSemaphore.await();

        String path1 = zookeeper.create(
                "/zk-test-ephemeral-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);

        log.info("Success create znode: {}", path1);

        String path2 = zookeeper.create(
                "/zk-test-ephemeral-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);

        log.info("Success create znode: {}", path2);

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
