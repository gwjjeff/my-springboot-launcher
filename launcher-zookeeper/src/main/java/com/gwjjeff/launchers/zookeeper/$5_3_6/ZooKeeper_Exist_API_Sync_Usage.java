package com.gwjjeff.launchers.zookeeper.$5_3_6;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jeff on 2017/5/19.
 */
@Slf4j
public class ZooKeeper_Exist_API_Sync_Usage implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk;

    public static void run() throws Exception {
        String path = "/zk-book";

        zk = new ZooKeeper(
                "localhost:2181",
                5000,
                new ZooKeeper_Exist_API_Sync_Usage()
        );

        connectedSemaphore.await();

        zk.exists(path, true);
        zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.setData(path, "123".getBytes(), -1);
        zk.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.delete(path + "/c1", -1);
        zk.delete(path, -1);

    }

    @Override
    public void process(WatchedEvent event) {
        try {
            if (Event.KeeperState.SyncConnected == event.getState()) {
                if (Event.EventType.None == event.getType() && null == event.getPath()) {
                    connectedSemaphore.countDown();
                } else if (Event.EventType.NodeCreated == event.getType()) {
                    log.info("Node({}) Created");

                    zk.exists(event.getPath(), true);

                } else if (Event.EventType.NodeDataChanged == event.getType()) {
                    log.info("Node({}) DataChanged");
                    zk.exists(event.getPath(), true);
                } else if (Event.EventType.NodeDeleted == event.getType()) {
                    log.info("Node({}) Deleted");
                    zk.exists(event.getPath(), true);
                }
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
