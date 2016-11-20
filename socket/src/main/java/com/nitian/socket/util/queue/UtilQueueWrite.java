package com.nitian.socket.util.queue;

import java.util.Map;

import com.nitian.socket.EngineSocket;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.factory.Factory;
import com.nitian.socket.util.write.UtilWrite;
import com.nitian.util.log.LogType;

/**
 * 读消息队列：处理map数据
 *
 * @author 1036225283
 */
public class UtilQueueWrite extends UtilQueue<Map<String, String>> {


    private EngineSocket engineSocket;
    private UtilWrite httpWrite;
    private UtilWrite webSocketWrite;


    public UtilQueueWrite(EngineSocket engineSocket) {
        // TODO Auto-generated constructor stub
        this.engineSocket = engineSocket;
        httpWrite = Factory.getUtilHttpWrite(engineSocket.getClass().getName());
        webSocketWrite = Factory.getUtilWebSocketWrite(engineSocket.getClass().getName());
    }

    @Override
    public synchronized void handle(Map<String, String> map) {
        // TODO Auto-generated method stub
        log.dateInfo(LogType.time, this, "第四步：开始发送消息");
        log.dateInfo(LogType.time, this, "第五步：开始包装发送消息");
        String protocol = map.get(CoreType.protocol.toString());
        if (protocol.equals("HTTP")) {
            httpWrite.write(map, engineSocket);
        } else if (protocol.equals("WEBSOCKET")) {
            webSocketWrite.write(map, engineSocket);
        }
        log.dateInfo(LogType.time, this, "第五步：结束包装发送消息");
        log.info(LogType.thread, this, Thread.currentThread().toString());
        log.dateInfo(LogType.time, this, "第四步：结束发送消息");
    }

}
