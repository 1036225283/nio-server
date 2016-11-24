package com.nitian.socket.util.queue;

import com.nitian.socket.EngineSocket;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseProtocol;
import com.nitian.socket.util.thread.ThreadWebSocket;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * 解析HTTP/WEBSOCKET请求队列
 *
 * @author 1036225283
 */
public class UtilQueueSocketChannel extends UtilQueue<Map<String,String>> {


    private EngineSocket engineSocket;
    protected LogManager log = LogManager.getInstance();

    public UtilQueueSocketChannel(EngineSocket engineSocket) {
        // TODO Auto-generated constructor stub
        this.engineSocket = engineSocket;
    }

    @Override
    public synchronized void handle(Map<String,String> map) {
        // TODO Auto-generated method stub

        try {
            log.dateInfo(LogType.time, this, "第二步：开始解析http或者websocket数据");
            log.info(LogType.thread, this, "线程：读socket："
                    + Thread.currentThread().toString());
            String request = map.get(CoreType.request.toString());
            new UtilParseProtocol(request, map);

            String protocol = map.get(CoreType.protocol.toString());
//            if (protocol.equals("WEBSOCKET")) {
//                ThreadWebSocket webSocket = new ThreadWebSocket(socket);
//                boolean result = engineSocket.getListWebSocketThread()
//                        .put(webSocket);
//                if (result == false) {
//                    map.put(CoreType.sec_websocket_accept.toString(),
//                            "i am sorry");
//                } else {
//                    engineSocket.getPoolWebSocketThread().execute(
//                            webSocket);
//                }
//            }
            log.dateInfo(LogType.time, this, "第二步：结束解析http或者websocket数据");
            engineSocket.getEngineHandle().push(map);
            log.dateInfo(LogType.time, this, "第二步：投递读线程队列消息完毕");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("------------------------------------------------------------------------------------------");
            log.info(LogType.error, this, "error=" + e.getMessage());
        }
    }

}
