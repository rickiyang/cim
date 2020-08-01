package com.rim.handler.encoder;

import com.alibaba.fastjson.JSON;
import com.rim.message.ImBody;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author rickiyang
 * @date 2020-07-15
 * @Desc 编码器
 */
@Slf4j
public class CimEncoder extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof ImBody) {
            String data = JSON.toJSONString(msg);
            byte[] bytes = data.getBytes();
            int length = bytes.length;
            // 长度包含包长度int 4个字节
            ByteBuf out = Unpooled.directBuffer(length + 4);
            out.writeInt(length);
            out.writeBytes(data.getBytes());
            ctx.writeAndFlush(out, promise);
        } else {
            ctx.writeAndFlush(msg, promise);
        }
    }
}
