package com.nitian.handler.redis;

import redis.clients.jedis.Jedis;

/**
 * redis客户端
 * Created by 1036225283 on 2017/1/8.
 */
public class Redis {

    private Jedis jedis = new Jedis("www.1036225283.com", 6379);

    private static Redis instance = new Redis();

    public static Redis getInstance() {
        return instance;
    }

    private Redis() {
        jedis.auth("xws@123%789^asd");
    }

    public String set(String key, String value) {
        return jedis.set(key, value);
    }

    public String get(String key) {
        return jedis.get(key);
    }

    public long del(String key) {
        return jedis.del(key);
    }

    public static long getTime() {
        return System.nanoTime();
    }

}
