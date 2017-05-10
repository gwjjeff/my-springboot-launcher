package com.gwjjeff.controller;

import com.gwjjeff.launchers.udp.EchoServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jeff on 2017/3/9.
 */
@Slf4j
@RestController
@RequestMapping("/udp/echo/status")
public class UdpEchoStatusController {
    @Autowired
    EchoServer echoServer;

    @RequestMapping("/count")
    @ResponseBody
    public int count() {
        return echoServer.getClientCount();
    }

    @RequestMapping("/reply")
    @ResponseBody
    public boolean reply(@RequestParam(defaultValue = "ok") String msg) {
        return echoServer.sendToAll(msg);
    }
}
