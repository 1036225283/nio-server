package com.nitian.socket.test.handler;

import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;

import java.util.Map;

public class TestHandler extends Handler {

    @Override
    public void handle(Map<String, String> map) {
        // TODO Auto-generated method stub
        map.put(CoreType.result.toString(), "");
    }

}
