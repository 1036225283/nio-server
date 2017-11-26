package com.nitian.socket.util.protocol.write;


import com.nitian.socket.EngineSocketNIO;

import java.util.Map;

/**
 * 抽象协议写处理器
 */
public abstract class ProtocolWriteHandler {

    public ProtocolWriteHandler() {

    }

    public abstract byte[] handle(Map<String, String> map);


    private Map<String, String> map;


    private EngineSocketNIO engineSocket;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }


    public void setEngineSocket(EngineSocketNIO engineSocket) {
        this.engineSocket = engineSocket;
    }

    public EngineSocketNIO getEngineSocket() {
        return engineSocket;
    }

}
