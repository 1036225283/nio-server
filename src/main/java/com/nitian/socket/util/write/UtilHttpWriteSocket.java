package com.nitian.socket.util.write;

import com.nitian.socket.EngineSocketNIO;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.protocol.write.ProtocolHttpWriteHandler;
import com._1036225283.util.self.log.LogManager;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

/**
 * AIO 发送消息
 */
public class UtilHttpWriteSocket extends UtilWrite {

    protected static LogManager log = LogManager.getInstance();

    public synchronized void write(Map<String, Object> map, EngineSocketNIO engineSocket) {
        long applicationId = Long.valueOf(map.get(CoreType.applicationId
                .toString()).toString());
        Socket socket = null;
//                engineSocket.COUNT_STORE.remove(applicationId);
        byte[] bs = new ProtocolHttpWriteHandler().handle(map);
        try {
            socket.getOutputStream().write(bs);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e, "");
        } finally {
            engineSocket.POOL_MAP.repay(map);
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.error(e, "");
            }
        }
    }
}
