package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            Selector selector=Selector.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(10));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            List<SocketChannel> list=new ArrayList<>();
            while (true){
                int ready=selector.select();
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator=selectionKeySet.iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey=iterator.next();
                    if (selectionKey.isAcceptable()){
                        ServerSocketChannel server= (ServerSocketChannel) selectionKey.channel();
                        System.out.println("beforeConnecting");
                        SocketChannel channel=server.accept();
                        System.out.println("afterConnecting");
                        channel.configureBlocking(false);
                        ByteBuffer  buffer=ByteBuffer.allocate(16);
                        channel.register(selector,SelectionKey.OP_READ,buffer);
                        iterator.remove();
                    }
                    else if (selectionKey.isReadable()){
                        SocketChannel channel= (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer= (ByteBuffer) selectionKey.attachment();
                        System.out.println("beforeReading");
                        int read = channel.read(buffer);
                        if (read==-1){
                            selectionKey.cancel();
                            channel.close();
                        }else {
                            System.out.println("afterReading");
                            split(buffer);
                            if (buffer.position()==buffer.limit()){
                                ByteBuffer newb=ByteBuffer.allocate(buffer.capacity()*2);
                                buffer.flip();
                                newb.put(buffer);
                                selectionKey.attach(newb);
                            }
                            buffer.flip();
                        }
                    }else  if (selectionKey.isWritable()){
                        SocketChannel socketChannel= (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer= (ByteBuffer) selectionKey.attachment();
                        int write=socketChannel.write(buffer);
                        if (!buffer.hasRemaining()){
                            selectionKey.attach(null);
                            selectionKey.interestOps(0);
                        }
                    }
                    iterator.remove();
                }
//                //阻塞方法
//                SocketChannel socketChannel=serverSocketChannel.accept();
//                System.out.println("afterConnecting");
//                list.add(socketChannel);
//                for (SocketChannel channel:list){
////                    socketChannel.configureBlocking(false);
//                    System.out.println("beforeReading");
//                    channel.read(buffer);
//                    buffer.flip();
//                    String s= StandardCharsets.UTF_8.decode(buffer).toString();
//                    System.out.println(socketChannel.getLocalAddress()+": "+s);
//                    buffer.compact();
//                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    private static void split(ByteBuffer buffer) {
        buffer.flip();
        for(int i = 0; i < buffer.limit(); i++) {
            // 遍历寻找分隔符
            // get(i)不会移动position
            if (buffer.get(i) == '\n') {
                // 缓冲区长度
                int length = i+1-buffer.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                // 将前面的内容写入target缓冲区
                for(int j = 0; j < length; j++) {
                    // 将buffer中的数据写入target中
                    target.put(buffer.get());
                }
                // 打印结果
                System.out.println(target);
            }
        }
        // 切换为写模式，但是缓冲区可能未读完，这里需要使用compact
        buffer.compact();
    }
}
