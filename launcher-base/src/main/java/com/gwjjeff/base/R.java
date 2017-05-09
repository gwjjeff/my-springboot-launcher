package com.gwjjeff.base;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeff on 2017/4/10.
 */
@Configuration
@ConfigurationProperties("r")
public class R {
    public List<String> getLaunchers() {
        return launchers;
    }

    private List<String> launchers = new ArrayList<String>();

}
