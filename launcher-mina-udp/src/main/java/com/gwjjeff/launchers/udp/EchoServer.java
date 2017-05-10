package com.gwjjeff.launchers.udp;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by jeff on 2017/3/9.
 */
@Component
@Slf4j
public class EchoServer {
    @Autowired
    EchoHandler echoHandler;

    NioDatagramAcceptor dataAcceptor;

    public boolean start() {
        try {
            //从全局配置中，获取端口
            int port = 8600;
            dataAcceptor = new NioDatagramAcceptor();
            dataAcceptor.setHandler(echoHandler);
            DefaultIoFilterChainBuilder chain = dataAcceptor.getFilterChain();
            chain.addLast("logger", new LoggingFilter());
            chain.addLast("codec", new ProtocolCodecFilter(new
                    TextLineCodecFactory(Charset.forName("UTF-8"))));
            // chain.addLast("threadPool", new ExecutorFilter(threadPool));
            DatagramSessionConfig dcfg = dataAcceptor.getSessionConfig();
            dcfg.setReadBufferSize(4096);// 设置接收最大字节默认2048
            dcfg.setMaxReadBufferSize(65536);
            dcfg.setReceiveBufferSize(1024);// 设置输入缓冲区的大小
            dcfg.setSendBufferSize(1024);// 设置输出缓冲区的大小
            dcfg.setReuseAddress(true);// 设置每一个非主监听连接的端口可以重用

            dataAcceptor.bind(new InetSocketAddress(port));
            log.info("UDP服务器启动成功!端口号:" + port);
            return true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
    public int getClientCount() {
        return dataAcceptor.getManagedSessionCount();
    }
    public boolean sendToAll(String msg) {
        dataAcceptor.getManagedSessions().forEach((k, session) -> {
            if (session != null && session.isConnected()) {
                WriteFuture wf = session.write(msg);
                wf.awaitUninterruptibly(1000);
                if(!wf.isWritten()) {
                    Throwable tr = wf.getException();
                    if(tr != null)
                    {
                        log.error(tr.getMessage(), tr);
                    }
                }

            }
        });

        return true;
    }
}
