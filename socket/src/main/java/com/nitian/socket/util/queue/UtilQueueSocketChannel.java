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
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * 解析HTTP/WEBSOCKET请求队列
 *
 * @author 1036225283
 */
public class UtilQueueSocketChannel extends UtilQueue<SelectionKey> {


    private EngineSocket engineSocket;
    protected LogManager log = LogManager.getInstance();

    public UtilQueueSocketChannel(EngineSocket engineSocket) {
        // TODO Auto-generated constructor stub
        this.engineSocket = engineSocket;
    }

    @Override
    public synchronized void handle(SelectionKey selectionKey) {
        // TODO Auto-generated method stub
        log.dateInfo(LogType.time, this, "第二步：开始解析SocketChannel中的http或者websocket数据");

        try {
            String request = read(selectionKey);
            if (request == null) {
                return;
            } else {
                Map<String, String> map = engineSocket.getPoolMap().lend();
                new UtilParseProtocol(request, map);
                log.dateInfo(LogType.time, this, "解析协议结束");


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

                //存放异步标识
                long applicationId = engineSocket.getCountStore().put(
                        (SocketChannel) selectionKey.channel());
                map.put(CoreType.applicationId.toString(),
                        String.valueOf(applicationId));
                map.put(CoreType.size.toString(), String.valueOf(request.length()));
                engineSocket.getEngineHandle().push(map);

            }
        } catch (Exception e) {
            log.error(e, "read线程异常");
        }

        log.dateInfo(LogType.time, this, "push数据到handler线程了");

    }


    public synchronized String read(SelectionKey key) throws IOException {
        //首先，借取资源
        byte[] bs = engineSocket.getPoolByte().lend();
        ByteBuffer buffer = engineSocket.getPoolBuffer().lend();

        synchronized (key) {
            SocketChannel socketChannel = (SocketChannel) key.channel();


            int size;

            try {
                size = socketChannel.read(buffer);
            } catch (IOException e) {
                size = -2;
                log.error(e, "远程客户端关闭了");
            }


            if (size > 0) {
                buffer.flip();
                buffer.get(bs, 0, size);
            } else if (size == 0) {
                log.dateInfo(LogType.time, this, "读取的数据长度为0，需要释放key和其他资源");
                size = -3;
            } else if (size == -1) {
                log.dateInfo(LogType.time, this, "读取的数据长度为-1，需要释放key和其他资源");
                size = -2;
            }

            //偿还资源
            engineSocket.getPoolByte().repay(bs);
            engineSocket.getPoolBuffer().repay(buffer);

            if (size == -2) {
                key.channel().close();
                key.cancel();
                return null;
            } else if (size == -3) {
                return null;
            }
            return new String(bs, 0, size, "UTF-8");
        }

    }

}
