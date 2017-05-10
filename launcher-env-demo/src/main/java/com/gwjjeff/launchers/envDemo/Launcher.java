package com.gwjjeff.launchers.envDemo;

import com.gwjjeff.base.BaseDemoLauncher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by jeff on 2017/5/10.
 */
@Component
@Slf4j
public class Launcher extends BaseDemoLauncher {

    private final Environment environment;

    @Autowired
    public Launcher(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("====: {}", environment.getProperty("foo.bar"));
        log.info("====: {}", environment.getProperty("foo.bar1"));
    }

    @Override
    protected void doRun(ApplicationArguments args) throws Exception {

    }
}
