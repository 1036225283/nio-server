package com.nitian.socket;

import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.WriteTest;
import com.nitian.socket.util.pool.UtilPoolBuffer;
import com.nitian.socket.util.pool.UtilPoolByte;
import com.nitian.socket.util.pool.UtilPoolMap;
import com.nitian.socket.util.protocol.read.ProtocolReadFactory;
import com.nitian.socket.util.protocol.write.ProtocolWriteFactory;
import com.nitian.socket.util.queue.UtilQueue;
import com.nitian.socket.util.queue.UtilQueueSocketChannel;
import com.nitian.socket.util.queue.UtilQueueWrite;
import com.nitian.socket.util.store.CountStore;
import com.nitian.socket.util.store.CountStoreSelectionKey;
import com.nitian.util.log.LogManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
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
public class EngineSocketNIO {


    public LogManager log = LogManager.getInstance();

    public static Map<SelectionKey, String> SOCKET_MAP;

    /**
     * 业务引擎
     */
    public static EngineHandle engineHandle;
    private Integer port;
    public static CountStore COUNT_STORE;
    private ServerSocket serverSocket;
    private int poolMax = 800;
    private int poolTotal = 200;

    public static ProtocolReadFactory protocolReadFactory;
    public static ProtocolWriteFactory protocolWriteFactory;

    public static UtilQueue QUEUE_READ;

    public static UtilQueue QUEUE_WRITE;

    public static UtilPoolBuffer POOL_BUFFER;
    public static UtilPoolByte POOL_BYTE;
    public static UtilPoolMap POOL_MAP;


    // 通道管理器
    private Selector selector;

    int count = 0;

    public void start() throws IOException {

        this.selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(getPort()));
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        // System.out.println("服务端启动成功！");
        // 轮询访问selector
        boolean isFlag = true;
        while (isFlag) {
            // System.out.println("服务一直在运行");
            selector.select();
            Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
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
                        // System.out.println("accept...");
                        // System.out.println(UtilByte.toBin((byte)
                        // key.interestOps()));

                        this.accept(key);
                    } else if (key.isConnectable()) {
                        // System.out.println("connect...");
                        // System.out.println(UtilByte.toBin((byte)
                        // key.interestOps()));
                        this.connect(key);
                    } else if (key.isReadable()) {
                        // System.out.println("read...");
                        // System.out.println(UtilByte.toBin((byte)
                        // key.interestOps()));
                        key.interestOps(key.interestOps() ^ SelectionKey.OP_READ);
                        QUEUE_READ.push(key);
                    } else if (key.isWritable()) {
                        // this.write(key);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(e, "");
                }

            }

        }

    }

    public synchronized void read(SelectionKey key) throws IOException {
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel socketChannel = (SocketChannel) key.channel();
        byte[] bs = this.POOL_BYTE.lend();
        // 创建读取的缓冲区
        // ByteBuffer buffer = this.getPoolBuffer().lend();
        ByteBuffer buffer = ByteBuffer.allocate(64 * 1024);
        int size;

        try {
            size = socketChannel.read(buffer);
        } catch (IOException e) {
            // The remote forcibly closed the connection, cancel the selection
            // key and close the channel.
            key.cancel();
            socketChannel.close();
            log.error(e, "");
            return;
        }

        if (size > 0) {
            buffer.flip();
            buffer.get(bs, 0, size);
        } else if (size == 0) {
            return;
        } else if (size == -1) {
            // Remote entity shut the socket down cleanly. Do the same from our
            // end and cancel the channel.
            key.channel().close();
            key.cancel();
            return;
        }

        long applicationId = this.COUNT_STORE.put(socketChannel);

        Map<String, String> map = this.POOL_MAP.lend();
        if (map == null) {
        }
        map.put(CoreType.applicationId.toString(), String.valueOf(applicationId));
        map.put(CoreType.size.toString(), String.valueOf(size));

        String request = new String(bs, 0, size, "UTF-8");
        map.put(CoreType.request.toString(), request);
        buffer.clear();
        this.POOL_BYTE.repay(bs);
        this.POOL_BUFFER.repay(buffer);
        QUEUE_READ.push(map);
    }

    public void write(SocketChannel socketChannel, String value) throws IOException {
        byte[] bytes = WriteTest.getString("").getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        // socketChannel.close();

    }

    /**
     * 获取socketChannel的状态
     *
     * @param socketChannel
     */
    public void state(SocketChannel socketChannel) {
    }

    public void accept(SelectionKey key) throws IOException {
        count = count + 1;

        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

        SocketChannel socketChannel = serverSocketChannel.accept();

        if (socketChannel.isRegistered()) {
        } else {
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
    }

    public void connect(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
    }

    public EngineSocketNIO() {
        init();
    }

    public EngineSocketNIO(int port) {
        this.port = port;
        init();
    }

    public synchronized void callback(Object object) {
        SelectionKey key = (SelectionKey) object;
        key.interestOps(SelectionKey.OP_READ);
        selector.wakeup();

    }

    public void init() {

        Thread.currentThread().setName("线程:主轮询线程");

        SOCKET_MAP = new HashMap<>();
        protocolReadFactory = new ProtocolReadFactory();
        protocolWriteFactory = new ProtocolWriteFactory();

        COUNT_STORE = new CountStoreSelectionKey();
        POOL_BUFFER = new UtilPoolBuffer(poolMax, poolTotal, null);
        POOL_BYTE = new UtilPoolByte(poolMax, poolTotal, null);// socket读取缓冲区(lend:replay)
        POOL_MAP = new UtilPoolMap(poolMax, poolTotal);// 解析数据缓冲区(lend:)

        QUEUE_READ = new UtilQueueSocketChannel(this);
        QUEUE_WRITE = new UtilQueueWrite(this);

        // 开启解析线程
        new Thread(QUEUE_READ, "线程：读队列线程").start();
        new Thread(QUEUE_WRITE, "线程：写队列线程").start();
    }


    public Integer getPort() {
        return port;
    }
}
