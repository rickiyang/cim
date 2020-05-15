package com.rickiyang.learn.packageEvent5;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rickiyang
 * @date 2020-05-14
 * @Desc 自定义消息体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MsgReq {

    private byte type;

    private int length;

    private String content;


}
