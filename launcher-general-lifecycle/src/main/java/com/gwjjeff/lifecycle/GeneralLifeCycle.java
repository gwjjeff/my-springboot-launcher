package com.gwjjeff.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "playground.launcher.lifecycle", havingValue = "general")
public class GeneralLifeCycle implements SmartLifecycle {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Runnable onStartAction;

    private final Runnable onStopAction;

    public GeneralLifeCycle() {
        this(null, null);
    }

    public GeneralLifeCycle(
            @Autowired(required = false) Runnable onStartAction,
            @Autowired(required = false) Runnable onStopAction) {
        this.onStartAction = onStartAction;
        this.onStopAction = onStopAction;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        //or add your own on stop logic here
        logger.info("!! stop(with callback);");
        stop();
        callback.run();
    }

    @Override
    public void start() {
    //or add your own on start logic here
        if (!running) {
            running = true;
            logger.info("!! start; start action = {}", onStartAction);
            if (onStartAction != null) {
                onStartAction.run();
            }
        }
    }

    @Override
    public void stop() {
    //or add your own on stop logic here
        if (isRunning()) {
            running = false;
            logger.info("!! stop; stop action = {}", onStopAction);
            if (onStopAction != null) {
                onStopAction.run();
            }
        }
    }

    private boolean running;
    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        //our life cycle bean is the last in the start up queue and the first in the shutdown queue
        return Integer.MAX_VALUE;
    }
}