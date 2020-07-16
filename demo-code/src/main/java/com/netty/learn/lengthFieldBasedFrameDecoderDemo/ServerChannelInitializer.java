package com.netty.learn.lengthFieldBasedFrameDecoderDemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author: rickiyang
 * @date: 2020/3/15
 * @description:
 */
public class ServerChannelInitializer  extends ChannelInitializer<SocketChannel> {
@Override
protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(1024, // 帧的最大长度，即每个数据包最大限度
                        0, // 长度字段偏移量
                        4, // 长度字段所占的字节数
                        0, // 消息头的长度，可以为负数
                        4) // 需要忽略的字节数，从消息头开始，这里是指整个包

        );
//        pipeline.addLast("decoder", new StringDecoder());
        //pipeline.addLast("encoder", new StringEncoder());
        // 自己的逻辑Handler
        pipeline.addLast("handler", new PeServerHandler());
        }
}
