package com.nitian.socket;

import com.nitian.socket.core.DefaultHandler;
import com.nitian.socket.util.HandlerFactory;
import com.nitian.socket.util.UtilPoolThread;
import com.nitian.socket.util.queue.UtilQueueHandle;

import java.util.Map;

/**
 * Created by 1036225283 on 2016/11/13.
 * 业务引擎
 */
public class EngineHandle {


    private UtilPoolThread poolHandlerThread;
    private HandlerFactory handlerFactory;
    // 消息队列
    private UtilQueueHandle queueHandle;


    private EngineSocket engineSocket;

    public EngineHandle() {
        init();
    }


    public void init() {

//        poolHandlerThread = new UtilPoolThread(200);
        // {url:handler}业务处理器，不需要追踪         // 默认handler
        handlerFactory = new HandlerFactory();
        handlerFactory.regist("default", new DefaultHandler());

        // 待处理消息队列
        queueHandle = new UtilQueueHandle(this);
        new Thread(queueHandle, "线程：业务队列线程").start();


    }

    public void push(Map<String, String> map) {
        queueHandle.push(map);
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

    public void setEngineSocket(EngineSocket engineSocket) {
        this.engineSocket = engineSocket;
    }

    public EngineSocket getEngineSocket() {
        return engineSocket;
    }
}
