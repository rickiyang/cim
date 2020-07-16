package com.rim.handler;

import com.rim.base.Unpack;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * @author rickiyang
 * @date 2020-07-15
 * @Desc 解码器
 */
@Slf4j
public class CimDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        Unpack unpack = decode(msg);
        //为空的包抛弃
        if (unpack == null) {
            return;
        }
        out.add(unpack);
    }


    /**
     * 接收到的包的信息
     * 这里做了兼容 拆包 的处理
     *
     * @return
     */
    protected Unpack decode(ByteBuf buf) {
        int length = buf.readableBytes();
        if (length < 4) {
            log.warn("length not enough, need 4");
            return null;
        }
        // 在读取前标记readerIndex
        buf.markReaderIndex();
        int dataLen = buf.readInt();
        if (length - 4 < dataLen) {
            // 长度校验，字节流长度少于数据包长度，说明数据包拆包了，等待下一次字节流过来
            //重置读索引为0
            buf.resetReaderIndex();
            log.warn("cur package probability incur unpack，buf");
            return null;
        }
        byte[] bytes = new byte[dataLen];
        buf.readBytes(bytes);
        return new Unpack(bytes);
    }
}
