package com.rim.rimclient.client;

import com.rim.handler.CimDecoder;
import com.rim.handler.CimEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author rickiyang
 * @date 2020-06-15
 * @Desc
 */
public class ClientHandlerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                .addLast(new CimDecoder())
                .addLast(new CimEncoder())
                .addLast(new IdleStateHandler(0, 10, 0, TimeUnit.SECONDS))
                .addLast(new HeartbeatHandler())
                .addLast(new NettyClientHandler());
    }
}