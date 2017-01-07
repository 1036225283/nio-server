package com.nitian.socket.test.handler.keyValue;

import com.alibaba.fastjson.JSON;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;
import com.nitian.util.keyvalue.KeyValue;
import com.nitian.util.keyvalue.Result;

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
        String key = paramMap.get("setKey");
        if (key == null) {
            Result result = new Result();
            result.setValue(null);
            String json = JSON.toJSONString(result);
            map.put(CoreType.result.toString(), json);
            return;
        }
        Result result = keyValue.get(key);
        String json = JSON.toJSONString(result);
        map.put(CoreType.result.toString(), json);

    }

}
