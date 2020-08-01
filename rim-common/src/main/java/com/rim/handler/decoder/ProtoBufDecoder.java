package com.rim.handler.decoder;

import com.rim.protocol.protobuf.ImChat;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rickiyang
 * @date 2020-07-16
 * @Desc PB 格式解析器
 */
@Slf4j
@ChannelHandler.Sharable
public class ProtoBufDecoder extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof byte[]) {
            ImChat.ImChatReq imChatReq = ImChat.ImChatReq.parseFrom((byte[]) msg);
            ctx.fireChannelRead(imChatReq);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }


}
