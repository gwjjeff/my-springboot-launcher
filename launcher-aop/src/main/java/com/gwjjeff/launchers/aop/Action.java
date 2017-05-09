package com.gwjjeff.launchers.aop;

import java.lang.annotation.*;

/**
 * Created by jeff on 2017/5/6.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
