package com.netty.learn.packageEvent5;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class ClientChannelInitializer extends  ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new MyProtocolEncoder());
        /**
         *
         * MsgReq 对象 ：
         * byte type 字段占一个字节
         * int length 字段 占4个字节
         * 剩下的是消息体
         * 那么 这里的 参数设置：
         *
         * int maxFrameLength：可以设置你认为的小题最大上限
         * int lengthFieldOffset：长度字段从哪个位置开始读：第0个字节是type，长度是从1开始的
         * int lengthFieldLength：长度字段所占的字节数，int类型占4个字节
         * int lengthAdjustment：是否需要调整消息头的长度，即读取消息头是否需要偏移一下，我们这里不需要
         * int initialBytesToStrip：消息体是否需要忽略一些字节数，比如忽略消息头的长度，我们这里消息头也算在对象里面了所以不忽略
         * boolean failFast：如果读取长度不够是否快速失败
         *
         */
        pipeline.addLast(new MyProtocolDecoder(1024,
                                                1,
                                                4,
                                                0,
                                                0,
                                                        true));
        // 客户端的逻辑
        pipeline.addLast("handler", new PeClientHandler());
    }
}
