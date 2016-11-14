package com.nitian.socket;

import com.nitian.socket.core.ApplicationSocket;
import com.nitian.socket.util.UtilPoolThread;
import com.nitian.socket.util.list.UtilListWebSocketThread;
import com.nitian.socket.util.pool.UtilPoolByte;
import com.nitian.socket.util.pool.UtilPoolMap;

import java.util.Map;

/**
 * Created by 1036225283 on 2016/11/13.
 * push
 */
public interface EngineSocket {

    /**
     * 将消息推送给业务引擎
     *
     * @param map
     */
    public void push(Map<String, String> map);


    /**
     * 获取对象池
     *
     * @return
     */
    public UtilPoolByte getPoolByte();

    /**
     * 获取消息池
     *
     * @return
     */
    public UtilPoolMap getPoolMap();

    /**
     * 获取Socket 计数
     *
     * @return
     */
    public ApplicationSocket getApplicationSocket();

    /**
     * 获取webSocket线程
     *
     * @return
     */
    public UtilListWebSocketThread getListWebSocketThread();

    /**
     * 获取webSocket相关的东西
     *
     * @return
     */
    public UtilPoolThread getPoolWebSocketThread();
}
