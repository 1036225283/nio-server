package com.nitian.socket.util.write;

import com.nitian.socket.EngineSocketNIO;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.protocol.write.ProtocolWebSocketUpgradeWriteHandler;
import com._1036225283.util.self.log.LogManager;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

/**
 * NIO 发送web socket 消息
 */
public class UtilWebSocketWriteSocketChannel extends UtilWrite {

    protected static LogManager log = LogManager.getInstance();

    public synchronized void write(Map<String, Object> map, EngineSocketNIO engineSocket) {
        long applicationId = Long.valueOf(map.get(CoreType.applicationId
                .toString()).toString());
        Socket socket = null;
//                EngineSocketNIO.COUNT_STORE.remove(
//                        applicationId);
        byte[] bs = new ProtocolWebSocketUpgradeWriteHandler().handle(map);
        try {
            socket.getOutputStream().write(bs);
        } catch (IOException e) {
            log.error(e, "");
        } finally {
            EngineSocketNIO.POOL_MAP.repay(map);
        }
    }
}
