package com.nitian.socket.util.parse;

import java.util.Date;
import java.util.Map;

import com.nitian.socket.core.CoreType;

public class UtilParseHttpWrite {

    public static byte[] getResult(Map<String, String> map) {
        map.get(CoreType.sessionId.toString());
        String result = map.get(CoreType.result.toString());
        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 200 OK").append("\r\n");
        sb.append("Server: Apache-Coyote/1.1").append("\r\n");
        sb.append(
                "Set-Cookie: JSESSIONID=641D11458BF06D2592E3BEFD612531D0; Path=/spring-mvc/; HttpOnly")
                .append("\r\n");
        sb.append("Access-Control-Allow-Origin: *").append("\r\n");
        sb.append("Accept-Charset: utf-8").append("\r\n");
        sb.append("Content-Type: text/html;charset=UTF-8").append("\r\n");
        if (result == null) {
            sb.append("Content-Length: ").append(0).append("\r\n");
            sb.append("Date: ").append(new Date().toString()).append("\r\n")
                    .append("\r\n");
            // ---------------------------------------------------------------------------------------
            sb.append("");
        } else {
            System.out.println(result.getBytes().length);
            sb.append("Content-Length: ").append(result.getBytes().length + 2).append("\r\n");
            sb.append("\r\n").append("\r\n");
            // ---------------------------------------------------------------------------------------
            sb.append(result);
        }
        return sb.toString().getBytes();
    }
}
