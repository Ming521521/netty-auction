package com.example.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class NettyClientApp {
    public static void main(String[] args) throws InterruptedException, IOException {
        ChannelFuture future = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                }).connect(new InetSocketAddress(10));
//        future.addListener((ChannelFutureListener) future1 -> {
//            Channel channel= future1.channel();
//            channel.writeAndFlush("hhhhhh");
//        });
        future.
                sync();
        Channel channel = future.channel();
        Scanner scanner=new Scanner(System.in);
        new Thread(() -> {
            while (true){
                String msg=scanner.next();
                if ("q".equals(msg)){
                    channel.close();
                    break;
                }
                channel.writeAndFlush(msg);
            }
        },"inputThread").start();
        ChannelFuture closy=channel.closeFuture();
        System.out.println("close....");
    }
}
