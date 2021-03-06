package com.nitian.socket.util.parse;

import com.nitian.socket.core.CoreType;

import java.util.Map;

/**
 * 解析 http 协议
 * Created by xws on 7/17/17.
 */
public class UtilParseHttp {

    private String method;

    private String url;

    private String param = "";

    private String ip;

    private String port;

    private String sessionId;

    private int bodyIndex;

    private String body = "";

    private static String CRLF = "\r\n";


    public UtilParseHttp(String string, Map<String, Object> header) {
        int methodIndex = string.indexOf(CRLF);
        int bodyIndex = string.indexOf(CRLF + CRLF);
        String method = string.substring(0, methodIndex);
        String[] first = method.split(" ");
        this.method = first[0];
        header.put(CoreType.method.toString(), first[0]);
        String[] urls = first[1].split("[?]");
        this.url = urls[0];
        if (urls.length == 2) {
            this.param = this.param + urls[1];
        }
        String head = string.substring(methodIndex + 2, bodyIndex);
        String[] heads = head.split(CRLF);
        for (String s : heads) {
            String[] value = s.split(": ");
            header.put(value[0], value[1]);
        }
        body = string.substring(bodyIndex + 4, string.length());
        //获取ip
        String Host = header.get("Host").toString();
        String[] hosts = Host.split(":");
        this.ip = hosts[0];
        this.port = hosts[1];
        header.put(CoreType.url.toString(), this.url);
        header.put(CoreType.ip.toString(), hosts[0]);
        header.put(CoreType.port.toString(), hosts[1]);
        header.put(CoreType.param.toString(), this.param + "&" + this.body);

        header.put(CoreType.sessionId.toString(), getSessionId(header));

    }


    public String getSessionId(Map<String, Object> header) {

        String Cookie = header.get("Cookie").toString();
        if (Cookie == null) {
            return null;
        }
        String[] Cookies = Cookie.split(";");
        for (String value : Cookies) {
            String[] session = value.split("=");
            if (session[0].contains("JSESSIONID")) {
                return session[1];
            }
        }
        return sessionId;
    }
}
