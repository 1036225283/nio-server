package com.nitian.socket.util.write;

import com.nitian.socket.EngineSocket;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseHttpWrite;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class UtilHttpWrite {

    protected static LogManager log = LogManager.getInstance();

    public synchronized static void write(Map<String, String> map, EngineSocket engineSocket) {
        long applicationId = Long.valueOf(map.get(CoreType.applicationId
                .toString()));
        Socket socket = engineSocket.getCountStoreSocket().remove(
                applicationId);
        UtilParseHttpWrite httpWrite = new UtilParseHttpWrite(map);
        byte[] bs = httpWrite.getResult();
        try {
            socket.getOutputStream().write(bs);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            engineSocket.getPoolMap().repay(map);
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                log.dateInfo(LogType.error, null, "第三步：结束处理消息");
            }
        }
    }
}
