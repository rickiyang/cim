package com.rim.rimserver.server;

import com.rim.handler.CimDecoder;
import com.rim.handler.CimEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author rickiyang
 * @date 2020-06-12
 * @Desc
 */
public class NettyServerHandlerInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                .addLast(new CimDecoder())
                .addLast(new CimEncoder())
                //空闲检测
                .addLast(new IdleStateHandler(15, 0, 0, TimeUnit.SECONDS))
                .addLast(new ServerIdleStateHandler())
                .addLast(new NettyServerHandler());
    }
}
