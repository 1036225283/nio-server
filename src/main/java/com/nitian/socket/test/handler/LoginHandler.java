package com.nitian.socket.test.handler;

import java.util.Map;

import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;

public class LoginHandler extends Handler {

    @Override
    public void handle(Map<String, String> map) {
        // TODO Auto-generated method stub
//		 String result = UtilJson.objectToString(map);
        map.put(CoreType.result.toString(), "赵玉，我会不会慢慢喜欢上你");
    }

}