package com.rim.message;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author rickiyang
 * @date 2020-07-16
 * @Desc 消息类型转换
 */
public class ImChatConverter {

    /**
     * 将 ImChat 转为 ByteBuf
     *
     * @param imChat
     * @return
     */
    public static ByteBuf messageToByteBuf(ImChat imChat) {
        String strData = JSON.toJSONString(imChat);
        byte[] bytes = strData.getBytes();
        int length = bytes.length;
        // 长度包含包长度int 4个字节
        ByteBuf out = Unpooled.directBuffer(length + 4);
        out.writeInt(length);
        out.writeBytes(bytes);
        return out;
    }
}
