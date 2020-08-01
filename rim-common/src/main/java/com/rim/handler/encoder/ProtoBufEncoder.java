package com.rim.handler.encoder;

import com.rim.protocol.protobuf.ImChat;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rickiyang
 * @date 2020-07-16
 * @Desc PB 文件编码
 */
@Slf4j
@ChannelHandler.Sharable
public class ProtoBufEncoder extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof ImChat.ImChatReq) {
            ImChat.ImChatReq req = (ImChat.ImChatReq) msg;
            ByteBuf byteBuf = Unpooled.buffer();
            byte[] b = req.toByteArray();
            byteBuf.writeInt(b.length);
            byteBuf.writeBytes(b);
            ctx.writeAndFlush(byteBuf);
        } else {
            ctx.writeAndFlush(msg, promise);
        }
    }
}
