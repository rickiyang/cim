package com.rickiyang.learn.helloWorld;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: rickiyang
 * @date: 2020/3/15
 * @description:
 */
@Slf4j
public class HwClient {

    private  int port;
    private  String address;

    public HwClient(int port, String address) {
        this.port = port;
        this.address = address;
    }

    public void start(){
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ClientChannelInitializer());
        try {
            ChannelFuture future = bootstrap.connect(address,port).sync();
            future.channel().writeAndFlush("Hello world, i'm online");
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("client start fail",e);
        }finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        HwClient client = new HwClient(7788,"127.0.0.1");
        client.start();
    }
}
