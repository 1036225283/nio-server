package com.nitian.socket.util.queue;

import java.util.Map;

import com.nitian.socket.EngineHandle;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.util.log.LogType;

public class UtilQueueRead extends UtilQueue<Map<String, String>> {


    private EngineHandle engineHandle;

    public UtilQueueRead(EngineHandle engineHandle) {
        // TODO Auto-generated constructor stub
        this.engineHandle = engineHandle;
    }

    @Override
    public synchronized void handle(Map<String, String> t) {
        // TODO Auto-generated method stub
        log.dateInfo(LogType.time, this, "第三步：开始处理消息");
        String url = t.get(CoreType.url.toString());

        Handler handler = engineHandle.getHandlerFactory()
                .get(url);
        if (handler != null) {
            handler.setEngineHandle(engineHandle);
            handler.setMap(t);
            handler.handle(t);
            handler.afterHandle();
        }
        log.info(LogType.thread, this, Thread.currentThread().toString());
        log.dateInfo(LogType.time, this, "第三步：结束处理消息");
    }

}
