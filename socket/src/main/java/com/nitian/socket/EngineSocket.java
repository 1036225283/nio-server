package com.nitian.socket;

import com.nitian.socket.util.factory.Factory;
import com.nitian.socket.util.store.CountStore;
import com.nitian.socket.util.store.CountStoreSocket;
import com.nitian.socket.util.UtilPoolThread;
import com.nitian.socket.util.list.UtilListWebSocketThread;
import com.nitian.socket.util.pool.UtilPoolByte;
import com.nitian.socket.util.pool.UtilPoolMap;
import com.nitian.socket.util.queue.UtilQueue;
import com.nitian.socket.util.queue.UtilQueueSocket;
import com.nitian.socket.util.queue.UtilQueueWrite;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * Created by 1036225283 on 2016/11/13.
 * 消息引擎
 */
public class EngineSocket {

    private LogManager log = LogManager.getInstance();


    /**
     * 业务引擎
     */
    private EngineHandle engineHandle;
    private Integer port;
    private CountStore countStore;
    private ServerSocket serverSocket;
    private int poolMax = 800;
    private int poolTotal = 200;

    private UtilQueue queueRead;
    private UtilQueue queueWrite;
    private UtilPoolByte poolByte;
    private UtilPoolMap poolMap;

    public EngineSocket(int port) {
        this.port = port;
    }

    public EngineSocket() {
    }

    public void init() {

        countStore = Factory.getCountStore(this.getClass().getName());

        poolByte = new UtilPoolByte(poolMax, poolTotal, null);// socket读取缓冲区(lend:replay)
        poolMap = new UtilPoolMap(poolMax, poolTotal);// 解析数据缓冲区(lend:)

        queueRead = Factory.getReadQueue(this.getClass().getName(), this);
        queueWrite = Factory.getWriteQueue(this.getClass().getName(), this);

        //开启解析线程
        new Thread(queueRead, "线程：解析列线程").start();
        new Thread(queueWrite, "线程：写队列线程").start();
    }

    public void push(Map<String, String> map) {
        queueWrite.push(map);
    }

    public void start() throws IOException {
        init();
        if (port == null) {
            port = 8080;
        }
        Thread.currentThread().setName("线程：服务主线程");
        log.info(LogType.thread, this, Thread.currentThread().toString());
        serverSocket = new ServerSocket(port);
        log.info(LogType.debug, this, "server is start");
        while (true) {
            Socket socket = serverSocket.accept();
            log.dateInfo(LogType.time, this, "____________________________________________________");
            log.dateInfo(LogType.time, this, "第一步：接收socket开始");
            queueRead.push(socket);
//            applicationContext.getQueueRead().push(socket);
//            WriteTest writeTest = new WriteTest(socket);
//            writeTest.start();
            log.dateInfo(LogType.time, this, "第一步：接收socket结束");
        }
    }


    public void setEngineHandle(EngineHandle engineHandle) {
        this.engineHandle = engineHandle;
        engineHandle.setEngineSocket(this);
    }


    public UtilPoolByte getPoolByte() {
        return poolByte;
    }

    public CountStore getCountStore() {
        return countStore;
    }

    public UtilListWebSocketThread getListWebSocketThread() {
        return null;
    }

    public UtilPoolMap getPoolMap() {
        return poolMap;
    }

    public UtilPoolThread getPoolWebSocketThread() {
        return null;
    }


    public EngineHandle getEngineHandle() {
        return engineHandle;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public UtilQueue getQueueRead() {
        return queueRead;
    }

    public Integer getPort() {
        return port;
    }
}
