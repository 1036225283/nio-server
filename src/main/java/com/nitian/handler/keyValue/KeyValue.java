package com.nitian.handler.keyValue;

import _1036225283.com.keyValue.client.KeyValueClient;

/**
 * key-value客户端
 * Created by 1036225283 on 2017/1/8.
 */
public class KeyValue {


    private KeyValueClient client = null;

    private static KeyValue instance = new KeyValue();

    public static KeyValue getInstance() {
        return instance;
    }

    private KeyValue() {
        try {
            client = new KeyValueClient("localhost", 9999);
            client.auth("xwsKeyValue@#$");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void set(String key, String value) {
        client.set(key, value);
    }

    public String get(String key) {
        return client.get(key);
    }

    public void del(String key) {
        client.remove(key);
    }

    public static long getTime() {
        return System.nanoTime();
    }

}
