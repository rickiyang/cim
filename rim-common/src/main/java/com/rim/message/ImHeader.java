package com.rim.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rickiyang
 * @date 2020-07-15
 * @Desc 消息头定义
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImHeader {

    /**
     * 消息大类
     */
    private int bType = 1;
    /**
     * 消息小类
     */
    private int sType = 2;
    /**
     * 消息发送类型
     */
    private int cType = 3;

}
