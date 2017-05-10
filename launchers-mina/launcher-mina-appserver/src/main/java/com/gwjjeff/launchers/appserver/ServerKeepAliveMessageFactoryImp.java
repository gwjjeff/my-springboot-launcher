package com.gwjjeff.launchers.appserver;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

public class ServerKeepAliveMessageFactoryImp implements KeepAliveMessageFactory {
    @Override
    public boolean isRequest(IoSession ioSession, Object o) {
        if (o instanceof String && o.equals(Params.SEND)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isResponse(IoSession ioSession, Object o) {
        if (o instanceof String && o.equals(Params.RECEIVE)) {
            return true;
        }
        return false;
    }

    @Override
    public Object getRequest(IoSession ioSession) {
        return null;
    }

    @Override
    public Object getResponse(IoSession ioSession, Object o) {
        System.out.println("回发心跳...");
        return Params.RECEIVE;
    }
}