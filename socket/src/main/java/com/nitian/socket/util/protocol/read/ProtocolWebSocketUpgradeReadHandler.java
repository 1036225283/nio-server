package com.nitian.socket.util.protocol.read;

import com.nitian.socket.core.CoreProtocol;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseRead;
import com.nitian.socket.util.websocket.UtilWebSocket;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * Web socket Upgrade协议解析器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolWebSocketUpgradeReadHandler extends ProtocolReadHandler {

    protected static LogManager log = LogManager.getInstance();

    @Override
    public boolean handle(Map<String, String> map, ByteBuffer buffer, byte[] bs) {
        try {
            buffer.flip();
            int length = buffer.remaining();
            buffer.get(bs, 0, length);
            String request = new String(bs, 0, length);
            log.info(LogType.debug, "----WEB SOCKET UPGRADE 读取数据 = " + request);
            String[] strings = request.split("\r\n");
            String ip = UtilParseRead.getIp(strings);
            String port = UtilParseRead.getPort(strings);
            String secWebSocketKey = UtilParseRead.getSecWebSocketKey(strings);
            String secWebSocketAccept = UtilWebSocket.getSecWebSocketAccept(secWebSocketKey);
            map.put(CoreType.ip.toString(), ip);
            map.put(CoreType.port.toString(), port);
            map.put(CoreType.sec_websocket_accept.toString(), secWebSocketAccept);
            map.put(CoreType.protocol.toString(), CoreProtocol.WEBSOCKETUPGRADE.toString());
            map.put(CoreType.size.toString(), String.valueOf(request.length()));
            return true;
        } catch (Exception e) {
            log.error(e, "解析HTTP协议出错了!!!");
            return false;
        }
    }
}
