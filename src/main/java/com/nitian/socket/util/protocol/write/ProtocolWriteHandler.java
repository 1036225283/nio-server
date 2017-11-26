package com.nitian.socket.util.protocol.write;


import com.nitian.socket.EngineSocketNIO;

import java.util.Map;

/**
 * 抽象协议写处理器
 */
public abstract class ProtocolWriteHandler {

    public ProtocolWriteHandler() {

    }

    public abstract byte[] handle(Map<String, Object> map);


    private Map<String, Object> map;


    private EngineSocketNIO engineSocket;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }


    public void setEngineSocket(EngineSocketNIO engineSocket) {
        this.engineSocket = engineSocket;
    }

    public EngineSocketNIO getEngineSocket() {
        return engineSocket;
    }

}
