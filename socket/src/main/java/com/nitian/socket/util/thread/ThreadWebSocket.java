package com.nitian.socket.util.thread;

import com.nitian.socket.EngineHandle;
import com.nitian.socket.EngineSocket;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseWebSocketData;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

/**
 * WebSocket守护线程
 *
 * @author 1036225283
 */
public class ThreadWebSocket implements Runnable {


    protected LogManager log = LogManager.getInstance();
    private EngineSocket engineSocket;
    private EngineHandle engineHandle;

    private Socket socket;
    private boolean stop = true;

    public ThreadWebSocket(Socket socket, EngineSocket engineSocket, EngineHandle engineHandle) {
        // TODO Auto-generated constructor stub
        this.socket = socket;
        this.engineSocket = engineSocket;
        this.engineHandle = engineHandle;
    }

    @Override
    public synchronized void run() {
        // TODO Auto-generated method stub

        while (stop) {
            try {
                log.info(LogType.thread, this, "线程：读socket："
                        + Thread.currentThread().toString());
                byte[] bs = engineSocket.getPoolByte().lend();
                int size = socket.getInputStream().read(bs);
                if (size != -1) {
                    UtilParseWebSocketData.parse(bs, size);
                    long applicationId = engineSocket
                            .getCountStore().put(socket);
                    log.info(LogType.debug, this, "size=" + size);
                    Map<String, String> map = engineSocket.getPoolMap()
                            .lend();
                    map.put(CoreType.applicationId.toString(),
                            String.valueOf(applicationId));
                    map.put(CoreType.protocol.toString(), "WEBSOCKET");
                    map.put("result", "this is web socket");
                    map.put(CoreType.size.toString(), String.valueOf(size));
                    engineSocket.getPoolByte().repay(bs);// 偿还bytes给对象池
                    engineHandle.push(map);
                } else {
                    engineSocket.getPoolByte().repay(bs);
                    engineSocket.getListWebSocketThread().remove(this);
                    stop = false;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.error(e, "");
            }
        }
    }
}
