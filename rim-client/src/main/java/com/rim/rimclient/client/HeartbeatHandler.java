package com.rim.rimclient.client;

import com.rim.message.HeartBeatPacket;
import com.rim.message.ImChatConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author rickiyang
 * @date 2020-06-15
 * @Desc 客户端统一心跳包处理逻辑
 */
@Slf4j
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    @Resource
    private NettyClient nettyClient;


    /**
     * 检测心跳
     * 指定时间内未收到服务端发送的写事件，向服务端发送心跳
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                //向服务端送心跳包
                ByteBuf byteBuf = ImChatConverter.messageToByteBuf(HeartBeatPacket.ping("1"));
                //发送心跳消息，并在发送失败时关闭该连接
                ctx.writeAndFlush(byteBuf).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //如果运行过程中服务端挂了,执行重连机制
        EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(() -> nettyClient.start(), 10L, TimeUnit.SECONDS);
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("client exceptionCaught:" + cause.getMessage());
        ctx.channel().close();
    }
}

