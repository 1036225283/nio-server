package com.nitian.handler.redis;

import com.nitian.handler.UtilResult;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;

import java.util.Map;

/**
 * del key
 */
public class RemoveHandler extends Handler {

    private static Redis redis = Redis.getInstance();


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


        long startTime = Redis.getTime();
        long value = redis.del(key);
        long endTime = Redis.getTime();

        long nanosecond = endTime - startTime;

        map.put(CoreType.result.toString(), UtilResult.success(key, value + "", nanosecond));


    }

}
