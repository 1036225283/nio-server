package com.nitian.socket;

import com.nitian.socket.util.WriteTest;
import com.nitian.socket.util.factory.Factory;
import com.nitian.socket.util.pool.UtilPoolByte;
import com.nitian.socket.util.pool.UtilPoolMap;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by 1036225283 on 2016/11/17.
 */
public class EngineSocketNIO extends EngineSocket {


    // 通道管理器
    private Selector selector;

    Socket socket;

    public void start() throws IOException {

        init();
        this.selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(getPort()));
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务端启动成功！");
        // 轮询访问selector
        boolean isFlag = true;
        while (isFlag) {
            selector.select(1000);
            int size = this.selector.selectedKeys().size();
            System.out.println("size = " + size);
            Iterator<SelectionKey> ite = this.selector.selectedKeys()
                    .iterator();
            SelectionKey key;
            while (ite.hasNext()) {
                key = ite.next();
                // 删除已选的key,以防重复处理
                ite.remove();
                try {
                    if (key.isValid()) {
                        if (key.isAcceptable()) {

                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key
                                    .channel();

                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        }

                        if (key.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            getQueueRead().push(socketChannel);
//                            read(key);
//                            SocketChannel socketChannel = (SocketChannel) key.channel();
//                            Socket socket = socketChannel.socket();
//                            getQueueRead().push(socket);
//                            System.out.println("推送到解析队列里面了");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("888888888888888888888888888888888888888888888888888888888888");
                    e.printStackTrace();
                }
//                // 客户端请求连接事件
//                if (key.isAcceptable()) {
//                    ServerSocketChannel server = (ServerSocketChannel) key
//                            .channel();
//                    // 获得和客户端连接的通道
//                    SocketChannel channel = server.accept();
//                    // 设置成非阻塞
//                    channel.configureBlocking(false);
//                    // 获得了可读的事件
//                } else if (key.isReadable()) {
//                    read(key);
//                }

            }

        }


    }


    public void read(SelectionKey key) throws IOException {
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();
        // 创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1000);
        int length = channel.read(buffer);
        System.out.println("读取的数据长度为:" + length);
        if (length > 0) {
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            String value = new String(bytes, "UTF-8");
            System.out.println(value);
        }
        write(channel, null);
        System.out.println("发送消息完毕");
    }


    public void write(SocketChannel socketChannel, String value) throws IOException {
        byte[] bytes = WriteTest.getString().getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        socketChannel.close();

    }

    EngineSocketNIO() {
    }

    EngineSocketNIO(int port) {
        setPort(port);
    }

    public void test() {
    }
}
