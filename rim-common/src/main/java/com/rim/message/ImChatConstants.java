package com.rim.message;

/**
 * @author rickiyang
 * @date 2020-07-16
 * @Desc 消息相关常量
 */
public class ImChatConstants {


    /**
     * 大类： 包类型
     */
    public interface BigTypeEnum {
        int LOGIN_REQ = 1;// 登录包
        int PING = 2;//心跳包
        int PONG = 3;//心跳响应包
        int REAL = 4;//正文消息包
    }

    /**
     * 小类： 消息类型
     */
    public interface SmallTypeEnum {
        int TEXT = 1;//普通文本消息
        int PIC = 2;//图片消息
        int AUDIO = 3;//音频消息
        int VIDEO = 4;//视频消息
    }

    /**
     * 聊天类型： 群聊，单聊，批量发送
     */
    public interface ChatTypeEnum {
        int ONE2ONE = 1;//1对1
        int GROUP = 2;//群聊
        int ONE2MORE = 3;//1对多
    }

    /**
     * 心跳类型
     */
    public interface AckEnum {
        String PING = "ping";//请求
        String PONG = "pong";//回应
    }

}
