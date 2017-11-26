package com.nitian.handler;

import java.util.Map;

import com.nitian.socket.core.Handler;

public class ExitHandler extends Handler {

    @Override
    public void handle(Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.exit(0);
    }

}
