package com.gwjjeff.launchers.udp;

import com.gwjjeff.base.BaseDemoLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * Created by jeff on 2017/3/9.
 */
@Component("udp_launcher")
public class Launcher extends BaseDemoLauncher {
    @Autowired
    EchoServer echoServer;
    @Override
    public void doRun(ApplicationArguments args) throws Exception {
        echoServer.start();
    }
}
