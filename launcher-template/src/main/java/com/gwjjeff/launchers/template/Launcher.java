package com.gwjjeff.launchers.template;

import com.gwjjeff.base.BaseDemoLauncher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Slf4j
@Component("template_launcher")
public class Launcher extends BaseDemoLauncher {
    @Override
    public void doRun(ApplicationArguments args) throws Exception {
        log.info("=============  TEMPLATE LAUNCHER  ===============");
    }
}