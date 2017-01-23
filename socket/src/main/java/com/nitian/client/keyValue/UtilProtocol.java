package com.nitian.client.keyValue;

import com.nitian.socket.core.CoreProtocol;
import com.nitian.socket.core.CoreType;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义协议
 * Created by 1036225283 on 2016/12/18.
 */
public class UtilProtocol {


    // 写数据
    public static String write(String url, String message, String ip, int port) {
        StringBuffer sb = new StringBuffer();
        sb.append("XWS:XWS/1.1");
        sb.append("\r\n");
        sb.append("Url:" + url);//设置url，每个url都有对应的handler
        sb.append("\r\n");
        sb.append("Accept-Language:en-us,zh-cn;q=0.5");
        sb.append("\r\n");
        sb.append("Accept-Encoding:gzip,deflate");
        sb.append("\r\n");
        sb.append("Host:" + ip);
        sb.append("\r\n");
        sb.append("Port:" + port);
        sb.append("\r\n");
        sb.append("User-Agent:XWS-CLIENT");
        sb.append("\r\n");
        sb.append("Connection:keep-alive");
        sb.append("\r\n");
        sb.append("Content:");
        sb.append(encode(message));
        return sb.toString();
    }

    // 读数据
    public static Map<String, String> read(String message) {
        Map<String, String> map = new HashMap<>();
        String[] strings = message.split("\r\n");
        String ip = find(strings, "Host");
        String port = find(strings, "Port");
        String url = find(strings, "Url");
        String param = find(strings, "Content");
        map.put(CoreType.ip.toString(), ip);
        map.put(CoreType.port.toString(), port);
        map.put(CoreType.url.toString(), url);
        map.put(CoreType.param.toString(), param);
        map.put(CoreType.protocol.toString(), CoreProtocol.XWS.toString());
        map.put(CoreType.size.toString(), String.valueOf(message.length()));
        map.put(CoreType.close.toString(), "false");
        return map;
    }

    public static String find(String[] strings, String key) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].indexOf(key) == 0) {
                return strings[i].split(":")[1];
            }
        }
        return null;
    }

    public static String encode(String param) {
        try {
            return URLEncoder.encode(param, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String decode(String param) {
        try {
            return URLDecoder.decode(param, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
