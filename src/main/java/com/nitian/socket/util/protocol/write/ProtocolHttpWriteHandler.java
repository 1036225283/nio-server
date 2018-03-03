package com.nitian.socket.util.protocol.write;

import com._1036225283.util.self.log.LogManager;
import com.nitian.socket.core.CoreType;

import java.util.Date;
import java.util.Map;

/**
 * HTTP-Write协议解析器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolHttpWriteHandler extends ProtocolWriteHandler {

    protected static LogManager log = LogManager.getInstance();

    @Override
    public byte[] handle(Map<String, Object> map) {
        String sessionId = map.get(CoreType.sessionId.toString()).toString();
        Object result = map.get(CoreType.result.toString());
        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 200 OK").append("\r\n");
        sb.append("Server: Apache-Coyote/1.1").append("\r\n");
        if (sessionId != null) {
            sb.append("Set-Cookie: JSESSIONID=" + sessionId + "; Path=/; HttpOnly").append("\r\n");
        }
        sb.append("Access-Control-Allow-Credentials: true").append("\r\n");

        Object Origin = map.get("Origin");
        sb.append("Access-Control-Allow-Origin: ").append(Origin).append("\r\n");
        sb.append("Accept-Charset: utf-8").append("\r\n");
        sb.append("Content-Type: text/html;charset=UTF-8").append("\r\n");
        if (result == null) {
            sb.append("Content-Length: ").append(0).append("\r\n");
            sb.append("Date: ").append(new Date().toString()).append("\r\n").append("\r\n");
            // ---------------------------------------------------------------------------------------
            sb.append("");
        } else {
            sb.append("Content-Length: ").append(result.toString().getBytes().length + 2).append("\r\n");
            sb.append("\r\n").append("\r\n");
            // ---------------------------------------------------------------------------------------
            sb.append(result);
        }
        map.put(CoreType.close.toString(), "true");
        return sb.toString().getBytes();
    }
}
