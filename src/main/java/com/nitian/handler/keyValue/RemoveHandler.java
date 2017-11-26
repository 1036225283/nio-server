package com.nitian.handler.keyValue;

import com.nitian.handler.UtilResult;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;

import java.util.Map;

public class RemoveHandler extends Handler {


    private static KeyValue keyValue = KeyValue.getInstance();


    @Override
    public void handle(Map<String, Object> map) {
        // TODO Auto-generated method stub
        String param = map.get(CoreType.param.toString()).toString();
        Map<String, String> paramMap = UtilParam.getParam(param);
        String key = paramMap.get("key");
        if (key == null) {
            map.put(CoreType.result.toString(), UtilResult.keyIsNull("key is null"));
            return;
        }


        long startTime = System.nanoTime();
        keyValue.del(key);
        long endTime = System.nanoTime();

        long nanosecond = endTime - startTime;

        map.put(CoreType.result.toString(), UtilResult.success(key, "OK", nanosecond));


    }

}
