package com.nitian.client.keyValue;

/**
 * 自定义协议
 * Created by 1036225283 on 2016/12/18.
 */
public class UtilProtocol {


    public static String write(String message, String url) {
        StringBuffer sb = new StringBuffer();
        sb.append("XWS:XWS/1.1");
        sb.append("\r\n");
        sb.append("Url:" + url);//设置url，每个url都有对应的handler
        sb.append("\r\n");
        sb.append("Accept-Language:en-us,zh-cn;q=0.5");
        sb.append("\r\n");
        sb.append("Accept-Encoding:gzip,deflate");
        sb.append("\r\n");
        sb.append("Host:localhost");
        sb.append("\r\n");
        sb.append("Port:88");
        sb.append("\r\n");
        sb.append("User-Agent:XWS-CLIENT");
        sb.append("\r\n");
        sb.append("Connection:keep-alive");
        sb.append("\r\n");
        sb.append("Content:");
        sb.append(message);
        return sb.toString();
    }

    public static String read(String message) {
        StringBuffer sb = new StringBuffer();
        sb.append("XWS:XWS/1.1");
        sb.append("\r\n");
        sb.append("Url:/love");
        sb.append("\r\n");
        sb.append("Accept-Language:en-us,zh-cn;q=0.5");
        sb.append("\r\n");
        sb.append("Accept-Encoding:gzip,deflate");
        sb.append("\r\n");
        sb.append("Host:localhost");
        sb.append("\r\n");
        sb.append("Port:88");
        sb.append("\r\n");
        sb.append("User-Agent:XWS-CLIENT");
        sb.append("\r\n");
        sb.append("Connection:keep-alive");
        sb.append("\r\n");
        sb.append("Content:");
        sb.append(message);
        return sb.toString();
    }

}
