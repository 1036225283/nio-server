package com.nitian.socket.util.protocol.write;

import com.nitian.socket.core.CoreType;
import com.nitian.util.log.LogManager;

import java.util.Date;
import java.util.Map;

/**
 * Web-socket-Upgrade-Write协议解析器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolWebSocketUpgradeWriteHandler extends ProtocolWriteHandler {

    protected static LogManager log = LogManager.getInstance();

    @Override
    public byte[] handle(Map<String, String> map) {

        map.get(CoreType.sessionId.toString());
        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 101 Switching Protocols").append("\r\n");
        sb.append("Server: XWS-Coyote/1.1").append("\r\n");
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
        System.out.println(sb.toString());
        map.put(CoreType.close.toString(), "false");
        return sb.toString().getBytes();
    }
}
