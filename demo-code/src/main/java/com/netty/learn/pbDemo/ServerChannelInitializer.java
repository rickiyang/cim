package com.netty.learn.pbDemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

/**
 * @author: rickiyang
 * @date: 2020/3/15
 * @description:
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //pb编解码
        pipeline.addLast("decoder", new ProtobufDecoder(MsgEntity.MsgBodyReq.getDefaultInstance()));
        pipeline.addLast("encoder", new ProtobufEncoder());
        // 自己的逻辑Handler
        pipeline.addLast("handler", new PbServerHandler());
    }
}
