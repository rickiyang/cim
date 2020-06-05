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
public class PbClientHandler extends SimpleChannelInboundHandler {

    private int counter;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        MsgEntity.MsgBodyReq message = (MsgEntity.MsgBodyReq)msg;
        System.out.println(message.getMessage() + " count:" + ++counter + "----end----");
    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String content = "我是一条测试消息，快来读我吧，啦啦啦&";
        MsgEntity.MsgBodyReq build = MsgEntity.MsgBodyReq.newBuilder()
                .setMessage(new String(content.getBytes(), Charset.defaultCharset())).setType(0).build();
        ctx.writeAndFlush(build);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Client is close");
    }


}
