package com.rickiyang.learn.helloWorld;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: rickiyang
 * @date: 2020/3/15
 * @description:
 */
@Slf4j
public class HwClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("server say : " + msg.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client channelActive");
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Client is close");
    }


}
