package Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(16);
        try (SocketChannel socketChannel = SocketChannel.open()) {
           // socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(11));
            System.out.println("waiting");
            String s="hello";
            buffer.put(s.getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            socketChannel.write(buffer);
            while (true){

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
