package com.nitian.socket.util.queue;

import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.Engine;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.write.UtilHttpWrite;
import com.nitian.socket.util.write.UtilWebSocketWrite;
import com.nitian.util.log.LogType;

/**
 * 读消息队列：处理map数据
 *
 * @author 1036225283
 */
public class UtilQueueWrite extends UtilQueue<Map<String, String>> {


    private Engine engine;

    public UtilQueueWrite(Engine engine) {
        // TODO Auto-generated constructor stub
        this.engine = engine;
    }

    @Override
    public synchronized void handle(Map<String, String> map) {
        // TODO Auto-generated method stub
        log.dateInfo(LogType.time, this, "第四步：开始发送消息");
        log.dateInfo(LogType.time, this, "第五步：开始包装发送消息");
        String protocol = map.get(CoreType.protocol.toString());
        if (protocol.equals("HTTP")) {
            UtilHttpWrite.write(map, engine);
        } else if (protocol.equals("WEBSOCKET")) {
            UtilWebSocketWrite.write(map, engine);
        }
        log.dateInfo(LogType.time, this, "第五步：结束包装发送消息");
        log.info(LogType.thread, this, Thread.currentThread().toString());
        log.dateInfo(LogType.time, this, "第四步：结束发送消息");
    }

}
