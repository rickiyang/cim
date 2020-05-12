package com.rickiyang.learn.packageEvent1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class ClientChannelInitializer extends  ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 客户端的逻辑
        pipeline.addLast("handler", new PeClientHandler());
    }
}
