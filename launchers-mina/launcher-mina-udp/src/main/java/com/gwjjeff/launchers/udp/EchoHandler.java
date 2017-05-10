package com.gwjjeff.launchers.udp;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.SocketAddress;

/**
 * Created by jeff on 2017/3/9.
 */
@Component
@Slf4j
public class EchoHandler extends IoHandlerAdapter {

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        session.closeNow();
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {

        log.info("message received: {} from {}", message, session.getRemoteAddress());   // string

//        if (message instanceof IoBuffer) {
//            IoBuffer buffer = (IoBuffer) message;
//            SocketAddress remoteAddress = session.getRemoteAddress();
//            server.recvUpdate(remoteAddress, buffer.getLong());
//        }
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        
        log.info("Session closed...");
        SocketAddress remoteAddress = session.getRemoteAddress();
//        server.removeClient(remoteAddress);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {

        log.info("Session created...");

        SocketAddress remoteAddress = session.getRemoteAddress();
//        server.addClient(remoteAddress);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        log.info("Session idle...");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.info("Session Opened...");
    }
}
