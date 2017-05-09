package com.gwjjeff.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jeff on 2017/4/10.
 */
public abstract class BaseDemoLauncher implements ApplicationRunner {
    @Autowired
    protected R r;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String componentName = this.getClass().getAnnotation(Component.class).value();
        if (r.getLaunchers().contains(componentName)) {
            new Thread( () -> {
                try {
                    doRun(args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    protected abstract void doRun(ApplicationArguments args) throws Exception;
}
