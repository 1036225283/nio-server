package com.nitian.socket;

import com.nitian.socket.core.ApplicationSocket;
import com.nitian.socket.core.DefaultHandler;
import com.nitian.socket.util.HandlerFactory;
import com.nitian.socket.util.UtilPoolThread;
import com.nitian.socket.util.list.UtilListWebSocketThread;
import com.nitian.socket.util.pool.UtilPoolByte;
import com.nitian.socket.util.pool.UtilPoolMap;
import com.nitian.socket.util.queue.UtilQueueParse;
import com.nitian.socket.util.queue.UtilQueueRead;
import com.nitian.socket.util.queue.UtilQueueWrite;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

public class ApplicationContext {

    private static ApplicationContext context = new ApplicationContext();
    private int poolMax = 800;
    private int poolTotal = 200;

    //    private UtilPoolByte poolByte;
    private UtilPoolThread poolSocketThread;
    private UtilPoolThread poolHandlerThread;
    private UtilPoolThread poolWebSocketThread;

    private HandlerFactory handlerFactory;
    // 消息队列
    private UtilQueueRead queueRead;

    private ApplicationSocket applicationSocket;
    private UtilListWebSocketThread listWebSocketThread;

    private LogManager log = LogManager.getInstance();

    public ApplicationContext() {
        // TODO Auto-generated constructor stub

        log.putType(LogType.time.toString());
        // log.putType("websocket_frame");
        // 线程池不需要追踪
        poolSocketThread = new UtilPoolThread(200);
        poolHandlerThread = new UtilPoolThread(200);
        poolWebSocketThread = new UtilPoolThread(10);

        // 对象池需要追踪


        // {url:handler}业务处理器，不需要追踪
        handlerFactory = new HandlerFactory();

        // 读，写消息队列
        queueRead = new UtilQueueRead(this);

//        queueParse = new UtilQueueParse(this);
        new Thread(queueRead, "线程：读队列线程").start();


        // 默认handler
        handlerFactory.regist("default", new DefaultHandler());

        // 全局socket
        applicationSocket = new ApplicationSocket();
        listWebSocketThread = new UtilListWebSocketThread();

    }

    public static ApplicationContext getInstance() {
        return context;
    }


    public int getPoolMax() {
        return poolMax;
    }

    public void setPoolMax(int poolMax) {
        this.poolMax = poolMax;
    }

    public UtilPoolThread getPoolSocketThread() {
        return poolSocketThread;
    }

    public UtilPoolThread getPoolHandlerThread() {
        return poolHandlerThread;
    }

    public UtilQueueRead getQueueRead() {
        return queueRead;
    }

    public ApplicationSocket getApplicationSocket() {
        return applicationSocket;
    }

    public void setApplicationSocket(ApplicationSocket applicationSocket) {
        this.applicationSocket = applicationSocket;
    }


    public HandlerFactory getHandlerFactory() {
        return handlerFactory;
    }

    public void setHandlerFactory(HandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    public UtilListWebSocketThread getListWebSocketThread() {
        return listWebSocketThread;
    }

    public UtilPoolThread getPoolWebSocketThread() {
        return poolWebSocketThread;
    }


}
