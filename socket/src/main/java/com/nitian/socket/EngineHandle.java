package com.nitian.socket;

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


}
