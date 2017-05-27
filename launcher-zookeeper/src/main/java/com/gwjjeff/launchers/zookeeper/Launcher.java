package com.gwjjeff.launchers.zookeeper;

import com.gwjjeff.base.BaseDemoLauncher;
import com.gwjjeff.launchers.zookeeper.$5_3_1.Zookeeper_Constructor_Usage_Simple;
import com.gwjjeff.launchers.zookeeper.$5_3_1.Zookeeper_Constructor_Usage_With_SID_PASSWD;
import com.gwjjeff.launchers.zookeeper.$5_3_2.Zookeeper_Create_API_ASync_Usage;
import com.gwjjeff.launchers.zookeeper.$5_3_2.Zookeeper_Create_API_Sync_Usage;
import com.gwjjeff.launchers.zookeeper.$5_3_4.Zookeeper_GetChildren_API_ASync_Usage;
import com.gwjjeff.launchers.zookeeper.$5_3_4.Zookeeper_GetChildren_API_Sync_Usage;
import com.gwjjeff.launchers.zookeeper.$5_3_6.ZooKeeper_Exist_API_Sync_Usage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Slf4j
@Component("zookeeper_launcher")
public class Launcher extends BaseDemoLauncher {

    @Override
    public void doRun(ApplicationArguments args) throws Exception {
        log.info("=============  zookeeper LAUNCHER  ===============");
//        Zookeeper_Constructor_Usage_Simple.run();
//        Zookeeper_Constructor_Usage_With_SID_PASSWD.run();
//        Zookeeper_Create_API_Sync_Usage.run();
//        Zookeeper_Create_API_ASync_Usage.run();
//        Zookeeper_GetChildren_API_Sync_Usage.run();
//        Zookeeper_GetChildren_API_ASync_Usage.run();
        ZooKeeper_Exist_API_Sync_Usage.run();
    }
}