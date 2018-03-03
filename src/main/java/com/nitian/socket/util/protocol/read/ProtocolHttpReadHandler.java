package com.nitian.socket.util.protocol.read;

import com._1036225283.util.self.log.LogType;
import com.nitian.socket.EngineSocketNIO;
import com.nitian.socket.core.CoreProtocol;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.UtilSession;
import com.nitian.socket.util.key.UtilSelectionKey;
import com.nitian.socket.util.parse.UtilParseHttp;
import com._1036225283.util.self.log.LogManager;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.util.Map;

/**
 * HTTP协议解析器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolHttpReadHandler extends ProtocolReadHandler {

    protected static LogManager log = LogManager.getInstance();

    @Override
    public void handle(Map<String, Object> map, ByteBuffer buffer, byte[] bs) {
        SelectionKey selectionKey = null;
        long applicationId = 0;
        try {

            buffer.flip();
            int length = buffer.remaining();
            buffer.get(bs, 0, length);
            String request = new String(bs, 0, length);
            log.info(LogType.debug, "----HTTP读取数据 = " + request);

            new UtilParseHttp(request, map);

            String sessionId = map.get(CoreType.sessionId.toString()).toString();
            if (sessionId == null) {
                sessionId = UtilSession.createSessionId();
            } else {
                UtilSession.updateTime(sessionId);
            }
            map.put(CoreType.protocol.toString(), CoreProtocol.HTTP.toString());
            map.put(CoreType.size.toString(), String.valueOf(request.length()));
            map.put(CoreType.sessionId.toString(), sessionId);
            map.put(CoreType.close.toString(), CoreType.close.toString());


            selectionKey = (SelectionKey) map.get("selectionKey");
            //存放异步标识
            applicationId = EngineSocketNIO.COUNT_STORE.put(selectionKey);
            map.put(CoreType.applicationId.toString(),
                    String.valueOf(applicationId));
            EngineSocketNIO.POOL_BYTE.repay(bs);
            EngineSocketNIO.POOL_BUFFER.repay(buffer);
            EngineSocketNIO.engineHandle.push(map);

        } catch (Exception e) {
            log.error(e, "解析HTTP协议出错了!!!");
            UtilSelectionKey.cancel(selectionKey);
            EngineSocketNIO.COUNT_STORE.remove(applicationId);
        }
    }
}
