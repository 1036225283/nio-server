package com.nitian.socket.test.handler.keyValue;

import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.test.handler.UtilResult;
import com.nitian.socket.util.parse.UtilParam;
import com.nitian.util.keyvalue.KeyValue;
import com.nitian.util.keyvalue.Result;

import java.util.Map;

public class InitHandler extends Handler {


    private static KeyValue keyValue = KeyValue.getInstance();


    @Override
    public void handle(Map<String, String> map) {
        // TODO Auto-generated method stub

        for (int i = 0; i < 800000; i++) {
            System.out.println(i);
            keyValue.set(i + "", i + "");
        }

        map.put(CoreType.result.toString(), "init is end");
    }

}
