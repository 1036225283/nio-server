package com.nitian.socket;

import com.nitian.socket.core.ApplicationSocket;
import com.nitian.socket.util.UtilPoolThread;
import com.nitian.socket.util.list.UtilListWebSocketThread;
import com.nitian.socket.util.pool.UtilPoolByte;
import com.nitian.socket.util.pool.UtilPoolMap;
import com.nitian.socket.util.queue.UtilQueueParse;
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
    private ApplicationSocket applicationSocket;
    private ServerSocket serverSocket;
    private int poolMax = 800;
    private int poolTotal = 200;

    private UtilQueueParse queueParse;
    private UtilQueueWrite queueWrite;
    private UtilPoolByte poolByte;
    private UtilPoolMap poolMap;

    public EngineSocket(int port) {
        this.port = port;
        init();
    }

    public EngineSocket() {
        init();
    }

    public void init() {

        applicationSocket = new ApplicationSocket();

        poolByte = new UtilPoolByte(poolMax, poolTotal, null);// socket读取缓冲区(lend:replay)
        poolMap = new UtilPoolMap(poolMax, poolTotal);// 解析数据缓冲区(lend:)

        queueParse = new UtilQueueParse(this);
        queueWrite = new UtilQueueWrite(this);

        //开启解析线程
        new Thread(queueParse, "线程：解析列线程").start();
        new Thread(queueWrite, "线程：写队列线程").start();
    }

    public void push(Map<String, String> map) {
        queueWrite.push(map);
    }

    public void start() throws IOException {
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
            queueParse.push(socket);
//            applicationContext.getQueueParse().push(socket);
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

    public ApplicationSocket getApplicationSocket() {
        return applicationSocket;
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

    public UtilQueueParse getQueueParse() {
        return queueParse;
    }

    public Integer getPort() {
        return port;
    }
}
