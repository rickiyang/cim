package com.netty.learn.myProtocolEncoderDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

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
        MsgReq req = (MsgReq) msg;
        System.out.println("-----start------| "+ req.getContent() + " | ------end------");
        byte[] bytes = ("receive" + ++counter + "\n").getBytes();
        MsgReq build = MsgReq.builder().length(bytes.length).type((byte) 0)
                .content(new String(bytes, StandardCharsets.UTF_8)).build();
        ctx.writeAndFlush(build);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }

}
