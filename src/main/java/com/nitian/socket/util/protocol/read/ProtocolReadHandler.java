package com.nitian.socket.util.protocol.read;


import com.nitian.socket.EngineSocketNIO;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * 抽象协议处理器
 */
public abstract class ProtocolReadHandler {

    public ProtocolReadHandler() {

    }

    public abstract boolean handle(Map<String, String> map, ByteBuffer buffer, byte[] bs);


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
