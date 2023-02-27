package com.example.netty.pck;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;

import java.nio.charset.StandardCharsets;

public class NettyHandler {
    public static void main(String[] args) {
        NioEventLoopGroup group=new NioEventLoopGroup(2);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(group);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast("hanfler1", new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        System.out.println(Thread.currentThread().getName() + " Inbound handler 1");
                        // 父类该方法内部会调用fireChannelRead
                        // 将数据传递给下一个handler
                        super.channelRead(ctx, msg);
                    }
                });
                ch.pipeline().addLast("handler2", new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf buf = (ByteBuf) msg;
                        System.out.println(buf.toString(StandardCharsets.UTF_8));

                        System.out.println(Thread.currentThread().getName() + " Inbound handler 2");
                        // 执行write操作，使得Outbound的方法能够得到调用
                        ch.writeAndFlush(ctx.alloc().buffer().writeBytes("Server...".getBytes(StandardCharsets.UTF_8)));
                        super.channelRead(ctx,msg);
                    }
                });
                ch.pipeline().addLast("handler3", new ChannelOutboundHandlerAdapter() {
                    @Override
                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                        System.out.println(Thread.currentThread().getName() + " Outbound handler 1");
                        super.write(ctx, msg, promise);
                    }
                });
                ch.pipeline().addLast("hanlder4", new ChannelOutboundHandlerAdapter() {
                    @Override
                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                        System.out.println(Thread.currentThread().getName() + " Outbound handler 2");
                        super.write(ctx, msg, promise);

                    }
                });
            }
        });
        serverBootstrap.bind(10);
    }
}
