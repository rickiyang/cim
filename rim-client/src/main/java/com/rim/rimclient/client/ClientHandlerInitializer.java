package com.rim.rimclient.client;

import com.rim.handler.decoder.CimDecoder;
import com.rim.handler.decoder.ProtoBufDecoder;
import com.rim.handler.encoder.CimEncoder;
import com.rim.handler.encoder.ProtoBufEncoder;
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
                .addLast(new ProtoBufDecoder())
                .addLast(new CimEncoder())
                .addLast(new ProtoBufEncoder())
                .addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS))
                .addLast(new HeartbeatHandler())
                .addLast(new NettyClientHandler());
    }
}