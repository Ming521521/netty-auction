package com.ming.netty.cache;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

public class ChannelCache implements  Cache<String, Channel> {
    private  volatile ConcurrentHashMap<String, Channel> channels = new ConcurrentHashMap<String, Channel>();
    @Override
    public Channel getCache(String s) {
        return channels.get(s);
    }

    @Override
    public void addCache(String s, Channel channel) {
        channels.put(s,channel);
    }

    @Override
    public void putIfAbsentCache(String s, Channel channel) {
        channels.putIfAbsent(s,channel);
    }

    @Override
    public void remove(String s) {
        channels.remove(s);
    }
    public ConcurrentHashMap<String,Channel> values(){
        return channels;
    }
}
