package com.nitian.handler.keyValue;

import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.util.keyvalue.KeyValue;

import java.util.Map;

public class RemoveHandler extends Handler {


    private static KeyValue keyValue = KeyValue.getInstance();


    @Override
    public void handle(Map<String, String> map) {
        // TODO Auto-generated method stub
//		 String result = UtilJson.objectToString(map);
        map.put(CoreType.result.toString(), "赵玉，我会不会慢慢喜欢上你");
    }

}
