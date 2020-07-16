package com.rim.rimclient.client;

import com.rim.base.Unpack;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rickiyang
 * @date 2020-06-15
 * @Desc 客户端包处理逻辑
 */
@Slf4j
public class NettyClientHandler extends SimpleChannelInboundHandler<Unpack> {

    /**
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Unpack msg) throws Exception {
        log.info("client receive msg:" + msg.getString());
    }

    /**
     * 处理异常, 一般将实现异常处理逻辑的Handler放在ChannelPipeline的最后
     * 这样确保所有入站消息都总是被处理，无论它们发生在什么位置，下面只是简单的关闭Channel并打印异常信息
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}