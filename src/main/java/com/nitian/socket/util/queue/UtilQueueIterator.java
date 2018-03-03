package com.nitian.socket.util.queue;

import com._1036225283.util.self.log.LogManager;
import com.nitian.socket.EngineSocketNIO;

import java.util.Iterator;

/**
 * 解析HTTP/WEBSOCKET请求队列
 *
 * @author 1036225283
 */
public class UtilQueueIterator extends UtilQueue<Iterator> {


    private EngineSocketNIO engineSocket;
    protected LogManager log = LogManager.getInstance();

    public UtilQueueIterator(EngineSocketNIO engineSocket) {
        // TODO Auto-generated constructor stub
        this.engineSocket = engineSocket;
    }

    @Override
    public synchronized void handle(Iterator map) {
        // TODO Auto-generated method stub

    }

}
