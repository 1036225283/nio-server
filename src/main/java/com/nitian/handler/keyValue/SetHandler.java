package com.nitian.handler.keyValue;

import com.nitian.handler.UtilResult;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;
import com.nitian.util.keyvalue.KeyValue;
import com.nitian.util.keyvalue.Result;

import java.util.Map;

public class SetHandler extends Handler {


    private static KeyValue keyValue = KeyValue.getInstance();


    @Override
    public void handle(Map<String, String> map) {
        // TODO Auto-generated method stub
        String param = map.get(CoreType.param.toString());
        Map<String, String> paramMap = UtilParam.getParam(param);
        String key = paramMap.get("key");
        if (key == null) {
            map.put(CoreType.result.toString(), UtilResult.keyIsNull("key is null"));
            return;
        }
        String value = paramMap.get("value");
        if (value == null) {
            map.put(CoreType.result.toString(), UtilResult.keyIsNull("value is null"));
            return;
        }

        Result result = keyValue.set(key, value);
        map.put(CoreType.result.toString(), UtilResult.success(key, value, Long.valueOf(result.getTime())));
    }

}
