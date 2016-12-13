package com.nitian.socket.util.parse;

import java.nio.ByteBuffer;
import java.util.Map;

import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.websocket.UtilWebSocket;

/**
 * 解析http请求头部,解析成Map<String,String>
 *
 * @author 1036225283
 */
public class UtilParseWebSocketRead {

    public static void parse(Map<String, String> map, ByteBuffer buffer, byte[] bs) {
        String[] strings = null;
        String ip = UtilParseRead.getIp(strings);
        String port = UtilParseRead.getPort(strings);
        String secWebSocketKey = UtilParseRead.getSecWebSocketKey(strings);
        String secWebSocketAccept = UtilWebSocket
                .getSecWebSocketAccept(secWebSocketKey);
        map.put(CoreType.ip.toString(), ip);
        map.put(CoreType.port.toString(), port);
        map.put(CoreType.sec_websocket_accept.toString(), secWebSocketAccept);
        map.put(CoreType.protocol.toString(), "WEBSOCKET");
    }

}
