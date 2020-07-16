package com.rim.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author rickiyang
 * @date 2020-07-15
 * @Desc 消息体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImBody {

    /**
     * 消息序列号
     */
    private String reqId;
    /**
     * 发送客户端唯一标识
     */
    private String clientId;
    /**
     * 发送者
     */
    long sendUid;
    /**
     * 接收者
     */
    int recvUid;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 客户端发送消息时间戳
     */
    private long sendTimeStamp = 5;
    /**
     * 接收者 多个
     */
    private List<Long> recvUids;
    /**
     * 群聊id
     */
    private String groupId;
}
