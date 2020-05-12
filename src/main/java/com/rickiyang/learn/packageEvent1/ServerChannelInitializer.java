package com.rickiyang.learn.packageEvent1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author: rickiyang
 * @date: 2020/3/15
 * @description:
 */
public class ServerChannelInitializer  extends ChannelInitializer<SocketChannel> {
@Override
protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 自己的逻辑Handler
        pipeline.addLast("handler", new PeServerHandler());
        }
}
