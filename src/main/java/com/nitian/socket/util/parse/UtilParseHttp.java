package com.nitian.socket.util.parse;

import java.util.HashMap;
import java.util.Map;

/**
 * 解析 http 协议
 * Created by xws on 7/17/17.
 */
public class UtilParseHttp {

    private String method;

    private String url;

    private int bodyIndex;

    private Map<String, String> headler = new HashMap<>();

    private String body;

    private static String CRLF = "\r\n";


    public UtilParseHttp(String string) {
        int methodIndex = string.indexOf(CRLF);
        int bodyIndex = string.indexOf(CRLF + CRLF);
        method = string.substring(0, methodIndex);
        String head = string.substring(methodIndex + 2, bodyIndex);
        String[] heads = head.split(CRLF);
        for (String s : heads) {
            String[] value = s.split(":");
            headler.put(value[0], value[1]);
        }

        body = null;
    }

    public String getHeader(String key) {
        return headler.get(key);
    }

}
