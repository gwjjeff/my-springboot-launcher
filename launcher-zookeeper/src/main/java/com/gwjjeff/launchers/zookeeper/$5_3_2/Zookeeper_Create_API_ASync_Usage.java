package com.gwjjeff.launchers.zookeeper.$5_3_2;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jeff on 2017/5/17.
 */
@Slf4j
public class Zookeeper_Create_API_ASync_Usage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void run() throws Exception {
        ZooKeeper zookeeper = new ZooKeeper(
                "localhost:2181",
                5000,
                new Zookeeper_Create_API_ASync_Usage()
        );
        log.info("state: {}", zookeeper.getState());

        connectedSemaphore.await();

        zookeeper.create(
                "/zk-test-ephemeral-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                new IStringCallback(), "I am context");

        zookeeper.create(
                "/zk-test-ephemeral-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                new IStringCallback(), "I am context");

        zookeeper.create(
                "/zk-test-ephemeral-",
                "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL,
                new IStringCallback(), "I am context");

    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("Receive watched event: {}", watchedEvent);

        if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            connectedSemaphore.countDown();
        }
    }

    private static class IStringCallback implements AsyncCallback.StringCallback {

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            log.info("Create path result: [{}, {}, {}], real path name: {}", rc, path, ctx, name);
        }
    }
}
