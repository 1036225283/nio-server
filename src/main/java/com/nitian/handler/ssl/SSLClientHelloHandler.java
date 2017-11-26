package com.nitian.handler.ssl;

import com.nitian.handler.UtilResult;
import com.nitian.handler.keyValue.KeyValue;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;

import java.util.Map;

/**
 * get(key)
 */
public class SSLClientHelloHandler extends Handler {

    private static KeyValue keyValue = KeyValue.getInstance();


    @Override
    public void handle(Map<String, Object> map) {
        // TODO Auto-generated method stub
//        SSLClientHello hello  = (SSLClientHello) map.get("");
        String param = map.get(CoreType.param.toString()).toString();
        Map<String, String> paramMap = UtilParam.getParam(param);
        String key = paramMap.get("key");
        if (key == null) {
            map.put(CoreType.result.toString(), UtilResult.keyIsNull("key is null"));
            return;
        }


        String value;
        long startTime = System.nanoTime();
        value = keyValue.get(key);
        long endTime = System.nanoTime();

        long nanosecond = endTime - startTime;

        map.put(CoreType.result.toString(), UtilResult.success(key, value, nanosecond));

    }

}
