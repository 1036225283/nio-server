package com.nitian.socket.util.parse;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 从encode的字符串中提取参数
 * Created by 1036225283 on 2017/1/3.
 */
public class UtilParam {

    /**
     * 对浏览器传递过来的数据进行解码，分解，提取
     *
     * @param param 浏览器传递过来的参数
     * @return Map<String-String>
     */
    public static Map<String, String> getParam(String param) {
        Map<String, String> map = new HashMap<>();
        String[] lines = param.split("&");
        for (String line : lines) {
            String[] keyValue = line.split("=");
            String key = decode(keyValue[0]);
            String value = decode(keyValue[1]);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 对参数进行解码
     *
     * @param param String
     * @return String
     */
    private static String decode(String param) {
        String result;
        try {
            result = URLDecoder.decode(param, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return result;
    }
}
