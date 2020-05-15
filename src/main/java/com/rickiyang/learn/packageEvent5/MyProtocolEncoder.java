package com.rickiyang.learn.packageEvent5;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

/**
 * @author rickiyang
 * @date 2020-05-14
 * @Desc 自定义编码器
 */
public class MyProtocolEncoder extends MessageToByteEncoder {



    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        MsgReq req = (MsgReq) msg;
        out.writeByte(req.getType());
        out.writeInt(req.getLength());
        out.writeBytes(req.getContent().getBytes(StandardCharsets.UTF_8));
    }
}
