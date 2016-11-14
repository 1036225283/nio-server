package com.nitian.socket.util.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseWebSocketData;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

/**
 * WebSocket守护线程
 *
 * @author 1036225283
 */
public class ThreadWebSocket implements Runnable {

    private ApplicationContext applicationContext = ApplicationContext
            .getInstance();

    protected LogManager log = LogManager.getInstance();

    private Socket socket;
    private boolean stop = true;

    public ThreadWebSocket(Socket socket) {
        // TODO Auto-generated constructor stub
        this.socket = socket;
    }

    @Override
    public synchronized void run() {
        // TODO Auto-generated method stub

        while (stop) {
            try {
                log.info(LogType.thread, this, "线程：读socket："
                        + Thread.currentThread().toString());
                byte[] bs = applicationContext.getEngineSocket().getPoolByte().lend();
                int size = socket.getInputStream().read(bs);
                if (size != -1) {
                    UtilParseWebSocketData.parse(bs, size);
                    long applicationId = applicationContext.getEngineSocket()
                            .getApplicationSocket().put(socket);
                    log.info(LogType.debug, this, "size=" + size);
                    Map<String, String> map = applicationContext.getEngineSocket().getPoolMap()
                            .lend();
                    map.put(CoreType.applicationId.toString(),
                            String.valueOf(applicationId));
                    map.put(CoreType.protocol.toString(), "WEBSOCKET");
                    map.put("result", "this is web socket");
                    map.put(CoreType.size.toString(), String.valueOf(size));
                    applicationContext.getEngineSocket().getPoolByte().repay(bs);// 偿还bytes给对象池
                    applicationContext.getEngineHandle().push(map);
                } else {
                    applicationContext.getEngineSocket().getPoolByte().repay(bs);
                    applicationContext.getEngineSocket().getListWebSocketThread().remove(this);
                    stop = false;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.info(LogType.error, this, "error=" + e.getMessage());
            }
        }
    }
}
