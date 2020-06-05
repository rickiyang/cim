package com.rickiyang.learn.pbDemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;


public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new ProtobufDecoder(MsgEntity.MsgBodyReq.getDefaultInstance()));
        pipeline.addLast(new ProtobufEncoder());
        // 客户端的逻辑
        pipeline.addLast("handler", new PbClientHandler());
    }
}
