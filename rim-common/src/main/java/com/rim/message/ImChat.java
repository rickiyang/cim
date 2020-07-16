package com.rim.message;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rickiyang
 * @date 2020-07-15
 * @Desc 消息对象
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImChat {
    /**
     * 消息头
     */
    private ImHeader imHeader;
    /**
     * 消息体
     */
    private ImBody imBody;

}
