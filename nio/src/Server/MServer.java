package Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.security.Key;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MServer {
    public static void main(String[] args) {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            Thread.currentThread().setName("Boss");
            serverSocketChannel.bind(new InetSocketAddress(11));
            Selector Boss=Selector.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(Boss,SelectionKey.OP_ACCEPT);
            Worker[] workers=new Worker[4];
            AtomicInteger robin=new AtomicInteger(0);
            for (int i=0;i>workers.length;i++){
                workers[i]=new Worker("worker-"+i);
            }
            while (true){
                Boss.select();
                Set<SelectionKey> selectionKeys=Boss.selectedKeys();
                Iterator<SelectionKey> iterator=selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey=iterator.next();
                    iterator.remove();;
                    if (selectionKey.isAcceptable()){
                        SocketChannel socketChannel=serverSocketChannel.accept();
                        System.out.println("connected");
                        socketChannel.configureBlocking(false);
                        System.out.println("beforeRead");
                        workers[robin.getAndIncrement()%workers.length].register(socketChannel);
                        System.out.println("after read");
                    }
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    static class Worker implements  Runnable{
        private  Thread thread;
        private volatile  Selector selector;
        private String name;
        private  volatile  boolean started=false;

        private ConcurrentLinkedQueue<Runnable> queue;
        public  Worker(String name){
            this.name=name;
        }
        public  void register(final SocketChannel socketChannel) throws IOException {
            if (!started){
                thread=new Thread(this,name);
                selector=Selector.open();
                queue=new ConcurrentLinkedQueue<>();
                thread.start();
                started=true;
            }
            queue.add(() -> {
                try {
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            selector.wakeup();
        }
        @Override
        public void run() {
            try {
                //阻塞
                selector.select();
                Runnable task = queue.poll();
                if (task!=null){
                    task.run();
                }
                Set<SelectionKey> selectionKeys=selector.selectedKeys();
                Iterator<SelectionKey> iterator=selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key=iterator.next();
                    iterator.remove();
                    if (key.isReadable()){
                        SocketChannel socketChannel= (SocketChannel) key.channel();
                        ByteBuffer buffer=ByteBuffer.allocate(16);
                        socketChannel.read(buffer);
                        buffer.flip();
                        System.out.println(buffer);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
