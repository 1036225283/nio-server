package com.nitian.socket.util.queue;

import com.nitian.socket.EngineSocket;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseHttpWrite;
import com.nitian.socket.util.parse.UtilParseWebSocketWrite;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * 读消息队列：处理map数据
 *
 * @author 1036225283
 */
public class UtilQueueWrite extends UtilQueue<Map<String, String>> {


    private EngineSocket engineSocket;


    public UtilQueueWrite(EngineSocket engineSocket) {
        // TODO Auto-generated constructor stub
        this.engineSocket = engineSocket;
    }

    @Override
    public synchronized void handle(Map<String, String> map) {
        // TODO Auto-generated method stub
        log.dateInfo(LogType.time, this, "写消息开始处理");

        long applicationId = Long.valueOf(map.get(CoreType.applicationId
                .toString()));
        SelectionKey key = (SelectionKey) engineSocket.getCountStore().remove(
                applicationId);
        SocketChannel socketChannel = (SocketChannel) key.channel();

        if (!socketChannel.isConnected()) {
            return;
        }

        ByteBuffer byteBuffer = null;
        byte[] bs = null;

        String protocol = map.get(CoreType.protocol.toString());
        if (protocol.equals("HTTP")) {
            bs = UtilParseHttpWrite.getResult(map);
        } else if (protocol.equals("WEBSOCKET")) {
            bs = UtilParseWebSocketWrite.getResult(map);
        }

        try {
            byteBuffer = engineSocket.getPoolBuffer().lend();
            byteBuffer.put(bs);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error(e, "");
        } finally {
            engineSocket.getPoolMap().repay(map);
            engineSocket.getPoolBuffer().repay(byteBuffer);
            try {
                if (protocol.equals("HTTP")) {
                    socketChannel.close();
                } else if (protocol.equals("WEBSOCKET")) {
                    this.engineSocket.callback(socketChannel);
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.error(e, "");
            }
        }
        log.info(LogType.thread, this, Thread.currentThread().toString());
        log.dateInfo(LogType.time, this, "写消息队列处理结束");

    }

}
