package com.rim.message;

/**
 * @author rickiyang
 * @date 2020-07-16
 * @Desc 心跳包
 */
public class HeartBeatPacket {

    /**
     * 上行心跳包
     *
     * @param clientId
     * @return
     */
    public static ImChat ping(String clientId) {
        ImHeader header = ImHeader.builder().bType(ImChatConstants.BigTypeEnum.PING)
                .sType(ImChatConstants.SmallTypeEnum.TEXT).cType(ImChatConstants.ChatTypeEnum.ONE2ONE).build();
        ImBody body = ImBody.builder().content(ImChatConstants.AckEnum.PING)
                .clientId(clientId).sendTimeStamp(System.currentTimeMillis()).build();
        return ImChat.builder().imHeader(header).imBody(body).build();
    }

    /**
     * 下行心跳包
     *
     * @param clientId
     * @return
     */
    public static ImChat pong(String clientId) {
        ImHeader header = ImHeader.builder().bType(ImChatConstants.BigTypeEnum.PONG)
                .sType(ImChatConstants.SmallTypeEnum.TEXT).cType(ImChatConstants.ChatTypeEnum.ONE2ONE).build();
        ImBody body = ImBody.builder().content(ImChatConstants.AckEnum.PONG)
                .clientId(clientId).sendTimeStamp(System.currentTimeMillis()).build();
        return ImChat.builder().imHeader(header).imBody(body).build();
    }
}
