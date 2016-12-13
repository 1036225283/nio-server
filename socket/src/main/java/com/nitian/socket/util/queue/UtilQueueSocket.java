package com.nitian.socket.util.queue;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import com.nitian.socket.EngineSocket;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.FactoryProtocol;
import com.nitian.socket.util.thread.ThreadWebSocket;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

/**
 * 解析HTTP/WEBSOCKET请求队列
 *
 * @author 1036225283
 */
public class UtilQueueSocket extends UtilQueue<Socket> {


    private EngineSocket engineSocket;
    protected LogManager log = LogManager.getInstance();

    public UtilQueueSocket(EngineSocket engineSocket) {
        // TODO Auto-generated constructor stub
        this.engineSocket = engineSocket;
    }

    @Override
    public synchronized void handle(Socket socket) {
        // TODO Auto-generated method stub

        try {
            log.dateInfo(LogType.time, this, "第二步：开始解析http或者websocket数据");
            log.info(LogType.thread, this, "线程：读socket："
                    + Thread.currentThread().toString());
            byte[] bs = engineSocket.getPoolByte().lend();
            int size = socket.getInputStream().read(bs);
            if (size == -1) {
                socket.close();
                engineSocket.getPoolByte().repay(bs);
                return;
            }
            log.info(LogType.debug, this, "size=" + size);

            long applicationId = engineSocket.getCountStore().put(
                    socket);

            Map<String, String> map = engineSocket.getPoolMap().lend();
            if (map == null) {
                System.out.println("草你么，空指针了");
            }
            map.put(CoreType.applicationId.toString(),
                    String.valueOf(applicationId));
            map.put(CoreType.size.toString(), String.valueOf(size));

            new FactoryProtocol(new String(bs, 0, size), map);
            engineSocket.getPoolByte().repay(bs);// 偿还bytes给对象池
            String protocol = map.get(CoreType.protocol.toString());
            if (protocol.equals("WEBSOCKET")) {
                ThreadWebSocket webSocket = new ThreadWebSocket(socket);
                boolean result = engineSocket.getListWebSocketThread()
                        .put(webSocket);
                if (result == false) {
                    map.put(CoreType.sec_websocket_accept.toString(),
                            "i am sorry");
                } else {
                    engineSocket.getPoolWebSocketThread().execute(
                            webSocket);
                }
            }
            log.dateInfo(LogType.time, this, "第二步：结束解析http或者websocket数据");
            engineSocket.getEngineHandle().push(map);
            log.dateInfo(LogType.time, this, "第二步：投递读线程队列消息完毕");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e, "");
        }
    }

}
