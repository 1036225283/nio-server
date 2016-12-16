package com.nitian.socket;

import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.WriteTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 1036225283 on 2016/11/17.
 */
public class EngineSocketNIO extends EngineSocket<SelectionKey> {


    // 通道管理器
    private Selector selector;

    Map<SocketChannel, Integer> tMap = new HashMap<>();

    int count = 0;

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
            selector.select();
            Iterator<SelectionKey> ite = this.selector.selectedKeys()
                    .iterator();
            SelectionKey key;
            while (ite.hasNext()) {
                key = ite.next();
                ite.remove();
                try {

                    if (!key.isValid()) {
                        System.out.printf("无效的key\t" + key);
                        continue;
                    }

                    if (key.isAcceptable()) {
                        System.out.println("accept...");
                        this.accept(key);
                    } else if (key.isConnectable()) {
                        System.out.println("connect...");
                        this.connect(key);
                    } else if (key.isReadable()) {
                        System.out.println("read...");
                        key.cancel();
                        getQueueRead().push(key);
                    } else if (key.isWritable()) {
                        System.out.println("write...");
//                        this.write(key);
                    }
                } catch (Exception e) {
                    System.out.println("888888888888888888888888888888888888888888888888888888888888");
                    e.printStackTrace();
                    log.error(e, "");
                }

            }

        }


    }


    public synchronized void read(SelectionKey key) throws IOException {
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel socketChannel = (SocketChannel) key.channel();
        byte[] bs = this.getPoolByte().lend();
        // 创建读取的缓冲区
//        ByteBuffer buffer = this.getPoolBuffer().lend();
        ByteBuffer buffer = ByteBuffer.allocate(64 * 1024);
        int size;

        try {
            size = socketChannel.read(buffer);
        } catch (IOException e) {
            // The remote forcibly closed the connection, cancel the selection key and close the channel.
            System.out.println("远程客户端关闭了。。。");
            key.cancel();
            socketChannel.close();
            log.error(e, "");
            return;
        }


        System.out.println("读取的数据长度为:" + size);
        if (size > 0) {
            buffer.flip();
            buffer.get(bs, 0, size);
        } else if (size == 0) {
            System.out.println("00000000000000000000000000000000000000000000000000000");
            return;
        } else if (size == -1) {
            // Remote entity shut the socket down cleanly. Do the same from our end and cancel the channel.
            key.channel().close();
            key.cancel();
            System.out.println("-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1-1");
            return;
        }

        long applicationId = this.getCountStore().put(
                socketChannel);

        Map<String, String> map = this.getPoolMap().lend();
        if (map == null) {
            System.out.println("草你么，空指针了");
        }
        map.put(CoreType.applicationId.toString(),
                String.valueOf(applicationId));
        map.put(CoreType.size.toString(), String.valueOf(size));

        String request = new String(bs, 0, size, "UTF-8");
        map.put(CoreType.request.toString(), request);
        buffer.clear();
        this.getPoolByte().repay(bs);
        this.getPoolBuffer().repay(buffer);
        getQueueRead().push(map);
    }


    public void write(SocketChannel socketChannel, String value) throws IOException {
        byte[] bytes = WriteTest.getString("").getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
//        socketChannel.close();

    }

    /**
     * 获取socketChannel的状态
     *
     * @param socketChannel
     */
    public void state(SocketChannel socketChannel) {
        System.out.println("isConnected \t" + socketChannel.isConnected());
        System.out.println("isConnectionPending \t" + socketChannel.isConnectionPending());
        System.out.println("isBlocking \t" + socketChannel.isBlocking());
        System.out.println("isOpen \t" + socketChannel.isOpen());
        System.out.println("isRegistered \t" + socketChannel.isRegistered());
    }


    public void accept(SelectionKey key) throws IOException {
        System.out.println("我被调用了几次：" + count);
        count = count + 1;


        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key
                .channel();

        SocketChannel socketChannel = serverSocketChannel.accept();

        if (socketChannel.isRegistered()) {
            System.out.println("已结注册过");
        } else {
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("注册完毕。。。");
        }
    }

    public void connect(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        System.out.println(socketChannel);
    }

    public EngineSocketNIO() {
    }

    public EngineSocketNIO(int port) {
        setPort(port);
    }

    @Override
    public synchronized void callback(Object object) {
        try {
            SocketChannel socketChannel = (SocketChannel) object;
            if (socketChannel.isRegistered()) {
                System.out.println("已结注册过");
            } else {
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
                System.out.println("重新注册完毕。。。");
            }
        } catch (IOException e) {
            log.error(e, "重新注册异常");
        }

    }
}
