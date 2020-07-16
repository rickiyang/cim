package com.netty.learn.delimiterBasedFrameDecoderDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: rickiyang
 * @date: 2020/3/15
 * @description:
 */
@Slf4j
public class PeServerHandler extends SimpleChannelInboundHandler {

    private int counter;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("server channelActive");
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String)msg;
        System.out.println("-----start------| "+ message + " | ------end------");

        String content = "receive" + ++counter + "&";
        ctx.writeAndFlush(content);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

}
