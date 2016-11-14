package com.nitian.socket;

import com.nitian.socket.util.HandlerFactory;

import java.util.Map;

/**
 * Created by 1036225283 on 2016/11/14.
 * 业务引擎接口定义
 */
public interface EngineHandle {

    /**
     * 将消息推送给消息引擎
     *
     * @param map
     */
    public void push(Map<String, String> map);

    /**
     * 业务工厂
     *
     * @return
     */
    public HandlerFactory getHandlerFactory();


    /**
     * 设置消息引擎
     *
     * @param engineSocket
     */
    public void setEngineSocket(EngineSocket engineSocket);
}
