package com.nitian.socket.util.protocol.read;


import java.nio.ByteBuffer;
import java.util.Map;

/**
 * 抽象协议处理器
 */
public abstract class ProtocolReadHandler {

    public ProtocolReadHandler() {

    }

    public abstract void handle(Map<String, Object> map, ByteBuffer buffer, byte[] bs);


    private Map<String, String> map;


    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }


}
