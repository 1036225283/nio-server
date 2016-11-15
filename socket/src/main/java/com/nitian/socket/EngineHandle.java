package com.nitian.socket;

import com.nitian.socket.core.DefaultHandler;
import com.nitian.socket.util.HandlerFactory;
import com.nitian.socket.util.UtilPoolThread;
import com.nitian.socket.util.queue.UtilQueueRead;

import java.util.Map;

/**
 * Created by 1036225283 on 2016/11/13.
 * 业务引擎
 */
public class EngineHandle {


    private UtilPoolThread poolHandlerThread;
    private HandlerFactory handlerFactory;
    // 消息队列
    private UtilQueueRead queueRead;

    private EngineSocket engineSocket;

    EngineHandle() {
        init();
    }


    public void init() {

        poolHandlerThread = new UtilPoolThread(200);
        // {url:handler}业务处理器，不需要追踪         // 默认handler
        handlerFactory = new HandlerFactory();
        handlerFactory.regist("default", new DefaultHandler());

        // 待处理消息队列
        queueRead = new UtilQueueRead(this);
        new Thread(queueRead, "线程：读队列线程").start();


    }

    public void push(Map<String, String> map) {

    }

    public UtilPoolThread getPoolHandlerThread() {
        return poolHandlerThread;
    }

    public HandlerFactory getHandlerFactory() {
        return handlerFactory;
    }

    public void setHandlerFactory(HandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    public UtilQueueRead getQueueRead() {
        return queueRead;
    }

    public void setEngineSocket(EngineSocket engineSocket) {
        this.engineSocket = engineSocket;
    }
}
