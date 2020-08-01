package com.rim.rimserver.config;

import com.google.common.collect.Maps;
import io.netty.channel.Channel;

import java.util.Map;

/**
 * @author rickiyang
 * @date 2020-07-17
 * @Desc
 */
public class ChannelContext {

    private static Map<String, Channel> channelMap = Maps.newConcurrentMap();


    /**
     * 存储 channel
     *
     * @param clientId
     * @param channel
     */
    public static void saveChannel(String clientId, Channel channel) {
        channelMap.putIfAbsent(clientId, channel);
    }

    public static Channel getChannel(String clientId) {
        return channelMap.get(clientId);
    }
}
