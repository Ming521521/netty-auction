package com.example.netty.pck;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class NettyPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group=new NioEventLoopGroup();
        EventLoop eventLoop=group.next();
        DefaultPromise<Integer> promise=new DefaultPromise<>(eventLoop);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("afaf");
            promise.setSuccess(10);
        }).start();
        System.out.println(Thread.currentThread().getName()+":::"+promise.get());
    }
}
