package com.nitian.socket.util.protocol.read;

import com.nitian.socket.core.CoreProtocol;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Session;
import com.nitian.socket.util.parse.UtilParseHttp;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * HTTP协议解析器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolHttpReadHandler extends ProtocolReadHandler {

    protected static LogManager log = LogManager.getInstance();

    @Override
    public boolean handle(Map<String, Object> map, ByteBuffer buffer, byte[] bs) {
        try {

            buffer.flip();
            int length = buffer.remaining();
            buffer.get(bs, 0, length);
            String request = new String(bs, 0, length);
            log.info(LogType.debug, "----HTTP读取数据 = " + request);

            new UtilParseHttp(request, map);

            String sessionId = map.get(CoreType.sessionId.toString()).toString();
            if (sessionId == null) {
                sessionId = Session.createSessionId();
            } else {
                Session.updateTime(sessionId);
            }
            map.put(CoreType.protocol.toString(), CoreProtocol.HTTP.toString());
            map.put(CoreType.size.toString(), String.valueOf(request.length()));
            map.put(CoreType.sessionId.toString(), sessionId);
            map.put(CoreType.close.toString(), CoreType.close.toString());
            return true;
        } catch (Exception e) {
            log.error(e, "解析HTTP协议出错了!!!");
            return false;
        }
    }
}
