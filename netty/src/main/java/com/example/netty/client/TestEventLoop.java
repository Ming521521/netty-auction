package com.example.netty.client;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.TimeUnit;

public class TestEventLoop {
    public static void main(String[] args) {
        EventLoopGroup group=new NioEventLoopGroup(2);
        System.out.println(group.next());
        group.next().execute(() -> System.out.println(Thread.currentThread().getName()+"kid"));
        group.next().scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()+"dingshi"),0,10, TimeUnit.SECONDS);
        group.shutdownGracefully();
    }
}
