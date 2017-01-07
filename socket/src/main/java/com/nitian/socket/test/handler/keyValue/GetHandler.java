package com.nitian.socket.test.handler.keyValue;

import com.alibaba.fastjson.JSON;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;
import com.nitian.util.keyvalue.KeyValue;
import com.nitian.util.keyvalue.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * get(key)
 */
public class GetHandler extends Handler {

    private static KeyValue keyValue = new KeyValue();


    @Override
    public void handle(Map<String, String> map) {
        // TODO Auto-generated method stub
        String param = map.get(CoreType.param.toString());
        Map<String, String> paramMap = UtilParam.getParam(param);
        Map<String, String> resultMap = new HashMap<>();
        String key = paramMap.get("key");
        if (key == null) {
            resultMap.put("state", "failure");
            resultMap.put("msg", "key is null");
            resultMap.put("key", "");
            resultMap.put("value", "");
            resultMap.put("second", "");
            resultMap.put("millisecond", "");
            resultMap.put("microsecond", "");
            resultMap.put("nanosecond", "");
            String json = JSON.toJSONString(resultMap);
            map.put(CoreType.result.toString(), json);
            return;
        }
        Result result = keyValue.get(key);

        long nanosecond = Long.valueOf(result.getTime());

        resultMap.put("state", "success");
        resultMap.put("msg", "success");
        resultMap.put("key", key);
        resultMap.put("value", result.getValue());
        resultMap.put("second", (nanosecond / 1000 / 1000 / 1000) + "");
        resultMap.put("millisecond", (nanosecond / 1000 / 1000) + "");
        resultMap.put("microsecond", (nanosecond / 1000) + "");
        resultMap.put("nanosecond", nanosecond + "");
        String json = JSON.toJSONString(resultMap);
        map.put(CoreType.result.toString(), json);

    }

}
