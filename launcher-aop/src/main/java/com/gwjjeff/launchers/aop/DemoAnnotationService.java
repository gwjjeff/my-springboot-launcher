package com.gwjjeff.launchers.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by jeff on 2017/5/6.
 */
@Service
@Slf4j
public class DemoAnnotationService {
    @Action(name = "intercept by declared annotation")
    public void add() {
        log.info("CALL: {}: {}", getClass().getName(), Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
