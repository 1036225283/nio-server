package com.nitian.socket.util.protocol;

import com._1036225283.util.self.log.LogType;
import com.nitian.socket.core.CoreProtocol;
import com.nitian.socket.util.protocol.ssl.SSL;
import java.nio.ByteBuffer;



import com._1036225283.util.self.log.LogManager;

/**
 * 进行协议分发，不同的协议，调用不同的协议处理器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolDispatcher {


    protected static LogManager log = LogManager.getInstance();


    public static String dispatcher(ByteBuffer buffer, byte[] bs) {
        try {
            buffer.flip();
            int length = buffer.remaining();
            buffer.get(bs, 0, length);

            int value = SSL.getHandshakeProtocol(bs);
            if (value == 22) {
                return CoreProtocol.HTTPS.toString();
            }

            String request = new String(bs, 0, length);
            log.dateInfo(LogType.debug, log, "----HTTP分发数据 = " + request);


            if (request.startsWith("XWS")) {
                return CoreProtocol.XWS.toString();
            } else if (request.contains("Upgrade: websocket")) {
                return CoreProtocol.WEBSOCKETUPGRADE.toString();
            } else if (request.startsWith("GET") || request.startsWith("POST") || request.startsWith("DELETE") || request.startsWith("UPDATE")) {
                return CoreProtocol.HTTP.toString();
            }
        } catch (Exception e) {
            log.error(e, "鉴定协议出问题了!哈哈哈");
        }
        return null;
    }

}
