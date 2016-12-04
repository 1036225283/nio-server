package com.nitian.socket.util.parse;

import com.nitian.socket.core.CoreType;

import java.util.Date;
import java.util.Map;

public class UtilParseWebSocketWrite {


    public static byte[] getResult(Map<String, String> map) {
        map.get(CoreType.sessionId.toString());
        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 101 Switching Protocols").append("\r\n");
        sb.append("Server: Apache-Coyote/1.1").append("\r\n");
        sb.append("Upgrade: websocket").append("\r\n");
        sb.append("Connection: upgrade").append("\r\n");
        String secWebSocketAccept = map.get(CoreType.sec_websocket_accept
                .toString());
        sb.append("Sec-WebSocket-Accept: ").append(secWebSocketAccept)
                .append("\r\n");
        sb.append("Sec-WebSocket-Extensions: permessage-deflate")
                .append("\r\n");
        sb.append("Date: ").append(new Date().toString()).append("\r\n")
                .append("\r\n");
        return sb.toString().getBytes();
    }
}
