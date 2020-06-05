package com.rickiyang.learn.pbDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author: rickiyang
 * @date: 2020/3/15
 * @description:
 */
@Slf4j
public class PbServerHandler extends SimpleChannelInboundHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("server channelActive");
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        MsgEntity.MsgBodyReq message = (MsgEntity.MsgBodyReq) msg;
        System.out.println("-----start------| "+ message.getMessage() + " | ------end------");
        MsgEntity.MsgBodyReq build = MsgEntity.MsgBodyReq.newBuilder()
                .setMessage(new String("receive".getBytes(), Charset.defaultCharset())).setType(0).build();
        ctx.writeAndFlush(build);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }


}
