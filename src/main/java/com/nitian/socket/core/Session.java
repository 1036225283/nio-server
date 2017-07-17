package com.nitian.socket.core;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Session {


    static Map<String, Map<String, Object>> session = new HashMap<>();

    //获取session
    public static Map<String, Object> get(String key) {
        return session.get(key);
    }

    //更新session时间
    public static void updateTime(String sessionId) {
        Map<String, Object> map = session.get(sessionId);
        if (map != null) {
            map.put("dtUpdateTime", new Date());
        } else {
            map = new HashMap<>();
            session.put(sessionId, map);
        }
    }

    //创建sessionId
    public static String createSessionId() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        Map<String, Object> value = new HashMap<>();
        value.put("dtUpdateTime", new Date());
        session.put(uuid, value);
        return uuid;
    }


}
