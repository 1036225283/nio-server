package com.nitian.socket.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * session manager
 * Created by xws on 11/27/17.
 */
public class UtilSession {

    private static Map<String, Map<String, Object>> map = new HashMap<>();


    public static Map<String, Object> get(String strSessionId) {
        return map.get(strSessionId);
    }

    //根据sessionId获取key的value
    public static Object getValue(String strSessionId, String key) {
        Map<String, Object> tmp = map.get(strSessionId);
        if (tmp == null) {
            return null;
        }
        return tmp.get(key);
    }

    //根据sessionId设在key和value
    public static void set(String strSessionId, String key, Object value) {
        Map<String, Object> tmp = map.get(strSessionId);
        if (tmp == null) {
            return;
        }
        tmp.put(key, value);
    }

    //创建sessionId
    public static String createSessionId() {
        String strSessionId = UUID.randomUUID().toString();
        strSessionId = strSessionId.replace("-", "");
        Map<String, Object> tmp = new HashMap<>();
        tmp.put("dtUpdateTime", new Date());
        map.put(strSessionId, tmp);
        return strSessionId;
    }



    //更新session时间
    public static void updateTime(String strSessionId) {
        Map<String, Object> tmp = map.get(strSessionId);
        if (tmp != null) {
            tmp.put("dtUpdateTime", new Date());
        } else {
            tmp = new HashMap<>();
            map.put(strSessionId, tmp);
        }
    }

}
