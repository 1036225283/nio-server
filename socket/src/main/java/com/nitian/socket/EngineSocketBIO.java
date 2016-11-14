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
 * SOCKET ENGINE BIO
 */
public class EngineSocketBIO implements EngineSocket {

    private LogManager log = LogManager.getInstance();


    /**
     * 业务引擎
     */
    private EngineHandle engineHandle;
    private Integer port;
    private ServerSocket serverSocket;
    private int poolMax = 800;
    private int poolTotal = 200;

    private UtilQueueParse queueParse;
    private UtilQueueWrite queueWrite;
    private UtilPoolByte poolByte;
    private UtilPoolMap poolMap;

    public EngineSocketBIO(int port) {
        this.port = port;
        init();
    }

    public EngineSocketBIO() {
        init();
    }

    public void init() {

        poolByte = new UtilPoolByte(poolMax, poolTotal, null);// socket读取缓冲区(lend:replay)
        poolMap = new UtilPoolMap(poolMax, poolTotal);// 解析数据缓冲区(lend:)
        //开启解析线程
        new Thread(queueParse, "线程：解析列线程").start();


        queueParse = new UtilQueueParse(this);
        queueWrite = new UtilQueueWrite(this);
        new Thread(queueWrite, "线程：写队列线程").start();
    }

    @Override
    public void push(Map<String, String> map) {

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
//            applicationContext.getQueueParse().push(socket);
//            WriteTest writeTest = new WriteTest(socket);
//            writeTest.start();
            log.dateInfo(LogType.time, this, "第一步：接收socket结束");
        }
    }


    public EngineHandle getEngineHandle() {
        return engineHandle;
    }

    public void setEngineHandle(EngineHandle engineHandle) {
        this.engineHandle = engineHandle;
        engineHandle.setEngineSocket(this);
    }


    @Override
    public UtilPoolByte getPoolByte() {
        return poolByte;
    }

    @Override
    public ApplicationSocket getApplicationSocket() {
        return null;
    }

    @Override
    public UtilListWebSocketThread getListWebSocketThread() {
        return null;
    }

    @Override
    public UtilPoolMap getPoolMap() {
        return poolMap;
    }

    @Override
    public UtilPoolThread getPoolWebSocketThread() {
        return null;
    }
}
