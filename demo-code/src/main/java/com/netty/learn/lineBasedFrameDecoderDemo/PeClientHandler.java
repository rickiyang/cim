package com.netty.learn.lineBasedFrameDecoderDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: rickiyang
 * @date: 2020/3/15
 * @description:
 */
@Slf4j
public class PeClientHandler extends SimpleChannelInboundHandler {

    private int counter;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String)msg;
        System.out.println(message + " count:" + ++counter + "----end----");
    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client channelActive");
        String content = "我是一条测试消息，快来读我吧，啦啦啦\n";
        for (int i = 0; i < 100; i++) {
            ctx.writeAndFlush(content);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Client is close");
    }


}
