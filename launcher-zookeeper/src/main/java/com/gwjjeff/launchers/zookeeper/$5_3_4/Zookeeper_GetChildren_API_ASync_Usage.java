package com.gwjjeff.launchers.zookeeper.$5_3_4;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by jeff on 2017/5/19.
 */
@Slf4j
public class Zookeeper_GetChildren_API_ASync_Usage implements Watcher {
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;

    public static void run() throws Exception {
        String path = "/zk-book";

        zk = new ZooKeeper(
                "localhost:2181",
                5000,
                new Zookeeper_GetChildren_API_ASync_Usage()
        );

        connectedSemaphore.await();

        zk.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create(path + "/c1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        zk.getChildren(path, true, new IChildren2Callback(), null);

        zk.create(path + "/c2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        Thread.sleep(2000);
    }

    @Override
    public void process(WatchedEvent event) {
        if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
            if (Watcher.Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (Watcher.Event.EventType.NodeChildrenChanged == event.getType()) {
                try {
                    log.info("ReGet Child: {}", zk.getChildren(event.getPath(), true));
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class IChildren2Callback implements AsyncCallback, AsyncCallback.Children2Callback {

        @Override
        public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
            log.info("Get Children znode result: [response code: {}, param path: {}, ctx: {}, children list: {}, stat: {}]",
                    rc, path, ctx, children, stat);
        }
    }
}
