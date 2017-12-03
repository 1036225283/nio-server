package com.nitian.socket.core;

import com.nitian.socket.EngineSocketNIO;

import java.util.Map;

public abstract class Handler {

    public Handler() {

    }

    public abstract void handle(Map<String, Object> map);


    private Map<String, Object> map;

    public void afterHandle() {
        EngineSocketNIO.QUEUE_WRITE.push(map);
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }



}
