package com.nitian.socket;

import com.nitian.socket.util.pool.UtilPoolBuffer;
import com.nitian.socket.util.pool.UtilPoolByte;
import com.nitian.socket.util.pool.UtilPoolMap;
import com.nitian.socket.util.protocol.read.ProtocolReadFactory;
import com.nitian.socket.util.protocol.write.ProtocolWriteFactory;
import com.nitian.socket.util.queue.UtilQueueRead;
import com.nitian.socket.util.queue.UtilQueueWrite;
import com.nitian.socket.util.store.CountStoreSelectionKey;
import com.nitian.util.log.LogManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 服务器
 * Created by 1036225283 on 2016/11/17.
 */
public class EngineSocketNIO {


    public LogManager log = LogManager.getInstance();

    //<SelectionKey,Protocol>
    public static Map<SelectionKey, String> SOCKET_MAP;

    /**
     * 业务引擎
     */
    public static EngineHandle engineHandle;
    private Integer port;
    public static CountStoreSelectionKey COUNT_STORE;
    private int poolMax = 800;
    private int poolTotal = 200;

    public static ProtocolReadFactory protocolReadFactory;
    public static ProtocolWriteFactory protocolWriteFactory;

    public static UtilQueueRead QUEUE_READ;
    public static UtilQueueWrite QUEUE_WRITE;

    public static UtilPoolBuffer POOL_BUFFER;
    public static UtilPoolByte POOL_BYTE;
    public static UtilPoolMap POOL_MAP;


    // 通道管理器
    private static Selector selector;

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

    private void accept(SelectionKey key) throws IOException {
        count = count + 1;

        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

        SocketChannel socketChannel = serverSocketChannel.accept();

        if (socketChannel.isRegistered()) {
        } else {
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        }
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

    private void init() {

        Thread.currentThread().setName("线程:主轮询线程");

        SOCKET_MAP = new HashMap<>();
        protocolReadFactory = new ProtocolReadFactory();
        protocolWriteFactory = new ProtocolWriteFactory();

        COUNT_STORE = new CountStoreSelectionKey();
        POOL_BUFFER = new UtilPoolBuffer(poolMax, poolTotal, null);
        POOL_BYTE = new UtilPoolByte(poolMax, poolTotal, null);// socket读取缓冲区(lend:replay)
        POOL_MAP = new UtilPoolMap(poolMax, poolTotal);// 解析数据缓冲区(lend:)

        QUEUE_READ = new UtilQueueRead();
        QUEUE_WRITE = new UtilQueueWrite(this);

        // 开启解析线程
        new Thread(QUEUE_READ, "线程：读队列线程").start();
        new Thread(QUEUE_WRITE, "线程：写队列线程").start();
    }


    public Integer getPort() {
        return port;
    }
}
