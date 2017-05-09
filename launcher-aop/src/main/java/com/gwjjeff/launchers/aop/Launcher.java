package com.gwjjeff.launchers.aop;

import com.gwjjeff.base.BaseDemoLauncher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component("aop_launcher")
@Slf4j
public class Launcher extends BaseDemoLauncher {
    private final DemoAnnotationService demoAnnotationService;
    private final DemoMethodService demoMethodService;

    @Autowired
    public Launcher(
            DemoAnnotationService demoAnnotationService,
            DemoMethodService demoMethodService) {
        this.demoAnnotationService = demoAnnotationService;
        this.demoMethodService = demoMethodService;
    }

    @Override
    public void doRun(ApplicationArguments args) throws Exception {
        log.info("=============  AOP LAUNCHER  ===============");
        demoAnnotationService.add();
        demoMethodService.add();
    }
}