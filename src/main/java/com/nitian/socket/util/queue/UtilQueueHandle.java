package com.nitian.socket.util.queue;

import com._1036225283.util.self.log.LogType;
import com.nitian.socket.EngineHandle;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;

import java.util.Map;

//业务处理对列
public class UtilQueueHandle extends UtilQueue<Map<String, Object>> {


    private EngineHandle engineHandle;

    public UtilQueueHandle(EngineHandle engineHandle) {
        // TODO Auto-generated constructor stub
        this.engineHandle = engineHandle;
    }

    @Override
    public synchronized void handle(Map<String, Object> t) {
        // TODO Auto-generated method stub
        log.dateInfo(LogType.time, this, "第三步：开始处理消息");
        String url = t.get(CoreType.url.toString()).toString();

        Handler handler = engineHandle.getHandlerFactory()
                .get(url);
        if (handler != null) {
            handler.setMap(t);
            handler.handle(t);
            handler.afterHandle();
        }
        log.info(LogType.thread, this, Thread.currentThread().toString());
        log.dateInfo(LogType.time, this, "第三步：结束处理消息");
    }

}
