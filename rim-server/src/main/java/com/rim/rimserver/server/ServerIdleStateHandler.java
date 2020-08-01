package com.rim.rimserver.server;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketAddress;

/**
 * @author rickiyang
 * @date 2020-06-12
 * @Desc 空闲检测 服务端主动关闭空闲连接
 */
@Slf4j
public class ServerIdleStateHandler extends ChannelInboundHandlerAdapter {

    /**
     * 指定时间内未收到客户端发送的读事件
     * 关闭长时间闲置的连接
     */
    @Override
    public void userEventTriggered(final ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state() == IdleState.READER_IDLE) {
                final IdleStateEvent e = (IdleStateEvent) evt;
                final SocketAddress socketAddress = ctx.channel().remoteAddress();
                ctx.channel().close().addListener((ChannelFutureListener) future -> {
                    if (future.isSuccess()) {
                        log.info("close idle connect:" + socketAddress + " for " + e.state() + " done");
                    } else {
                        log.info("close idle connect:" + socketAddress + " for " + e.state() + " fail");
                    }
                });
            }
        }
        super.userEventTriggered(ctx, evt);
    }

}

