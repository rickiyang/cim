package com.rickiyang.learn.helloWorld;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author: rickiyang
 * @date: 2020/3/15
 * @description:
 */
public class ServerChannelInitializer  extends ChannelInitializer<SocketChannel> {
@Override
protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 字符串解码 和 编码
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());

        // 自己的逻辑Handler
        pipeline.addLast("handler", new HwServerHandler());
        }


}
