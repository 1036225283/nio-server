package com.nitian.socket.util.write;

import com.nitian.socket.EngineSocket;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.protocol.write.ProtocolHttpWriteHandler;
import com.nitian.util.log.LogManager;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

/**
 * AIO 发送消息
 */
public class UtilHttpWriteSocket extends UtilWrite {

    protected static LogManager log = LogManager.getInstance();

    public synchronized void write(Map<String, String> map, EngineSocket engineSocket) {
        long applicationId = Long.valueOf(map.get(CoreType.applicationId
                .toString()));
        Socket socket = (Socket) engineSocket.getCountStore().remove(
                applicationId);
        byte[] bs = new ProtocolHttpWriteHandler().handle(map);
        try {
            socket.getOutputStream().write(bs);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e, "");
        } finally {
            engineSocket.getPoolMap().repay(map);
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.error(e, "");
            }
        }
    }
}
