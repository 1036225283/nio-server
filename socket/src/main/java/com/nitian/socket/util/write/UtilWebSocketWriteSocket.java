package com.nitian.socket.util.write;

import com.nitian.socket.EngineSocket;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseWebSocketWrite;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

/**
 * BIO 发送web socket 消息
 */
public class UtilWebSocketWriteSocket extends UtilWrite {

    protected static LogManager log = LogManager.getInstance();

    public synchronized void write(Map<String, String> map, EngineSocket engineSocket) {
        long applicationId = Long.valueOf(map.get(CoreType.applicationId
                .toString()));
        Socket socket = (Socket) engineSocket.getCountStore().remove(
                applicationId);
        UtilParseWebSocketWrite webSocketWrite = new UtilParseWebSocketWrite(
                map);
        byte[] bs = webSocketWrite.getResult();
        try {
            socket.getOutputStream().write(bs);
        } catch (IOException e) {
            log.info(LogType.error, null, "error+" + e.getMessage());
        } finally {
            engineSocket.getPoolMap().repay(map);
        }
    }
}
