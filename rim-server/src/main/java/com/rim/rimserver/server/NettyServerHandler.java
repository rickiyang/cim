package com.rim.rimserver.server;

import com.alibaba.fastjson.JSON;
import com.rim.base.Unpack;
import com.rim.message.HeartBeatPacket;
import com.rim.message.ImChat;
import com.rim.message.ImChatConstants;
import com.rim.message.ImChatConverter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author rickiyang
 * @date 2020-06-12
 * @Desc 消息处理逻辑
 */
@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<Unpack> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Unpack pack) throws Exception {
        String req = pack.getString();
        log.info("receive client message,req = {}", req);
        ImChat imChat = JSON.parseObject(req, ImChat.class);

        switch (imChat.getImHeader().getBType()) {
            case ImChatConstants.BigTypeEnum.PING:
                //回应pong
                ctx.writeAndFlush(ImChatConverter.messageToByteBuf(HeartBeatPacket.pong("1")));
                break;
            case ImChatConstants.BigTypeEnum.LOGIN_REQ:
                //todo 记录登录状态
            case ImChatConstants.BigTypeEnum.REAL:
                //todo 发送消息
            default:
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("server exceptionCaught:" + cause.getMessage());
        ctx.channel().close();
    }
}
