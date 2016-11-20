package com.nitian.socket.util.write;

import com.nitian.socket.EngineSocket;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseHttpWrite;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * NIO 发送消息
 */
public class UtilHttpWriteSocketChannel extends UtilWrite {

    protected static LogManager log = LogManager.getInstance();

    public synchronized void write(Map<String, String> map, EngineSocket engineSocket) {
        long applicationId = Long.valueOf(map.get(CoreType.applicationId
                .toString()));
        SocketChannel socketChannel = (SocketChannel) engineSocket.getCountStore().remove(
                applicationId);
        UtilParseHttpWrite httpWrite = new UtilParseHttpWrite(map);
        ByteBuffer byteBuffer = null;
        byte[] bs = httpWrite.getResult();
        try {
            byteBuffer = engineSocket.getPoolBuffer().lend();
            byteBuffer.put(bs);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            engineSocket.getPoolMap().repay(map);
            engineSocket.getPoolBuffer().repay(byteBuffer);
            try {
                socketChannel.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                log.dateInfo(LogType.error, null, "第三步：结束处理消息");
            }
        }
    }
}
