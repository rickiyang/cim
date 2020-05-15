package com.rickiyang.learn;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author rickiyang
 * @date 2020-05-13
 * @Desc TODO
 */
public class MainTest {

    public static void main(String[] args) {
        ByteBuf message = Unpooled.buffer(1);
        message.writeBytes("1".getBytes());
        byte[] req = new byte[message.readableBytes()];
        System.out.println(1);
    }
}
