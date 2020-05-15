package com.rickiyang.learn.packageEvent5;

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
public class PeClientHandler extends SimpleChannelInboundHandler {

    private int counter;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        MsgReq buf = (MsgReq) msg;
        System.out.println(buf.getContent() + " count:" + ++counter + "----end----\n");
    }



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client channelActive");
        for (int i = 0; i < 100; i++) {
            byte[] req = ("我是一条测试消息，快来读我吧，啦啦啦" + i).getBytes();
            MsgReq build = MsgReq.builder().length(req.length).type((byte) 0)
                    .content(new String(req, StandardCharsets.UTF_8)).build();
            ctx.writeAndFlush(build);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Client is close");
    }


}
