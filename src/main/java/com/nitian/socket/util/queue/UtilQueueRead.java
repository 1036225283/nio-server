package com.nitian.socket.util.queue;

import com.nitian.socket.EngineSocketNIO;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.key.UtilSelectionKey;
import com.nitian.socket.util.protocol.ProtocolDispatcher;
import com.nitian.socket.util.protocol.read.ProtocolReadHandler;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * 解析HTTP/WEBSOCKET请求队列
 *
 * @author 1036225283
 */
public class UtilQueueRead extends UtilQueue<SelectionKey> {


    protected LogManager log = LogManager.getInstance();


    @Override
    public synchronized void handle(SelectionKey selectionKey) {
        // TODO Auto-generated method stub
        log.dateInfo(LogType.time, this, "第二步：开始解析SocketChannel中的http或者websocket数据");

        try {

            ByteBuffer buffer = UtilSelectionKey.read(selectionKey);
            if (buffer == null) {
                return;
            }
            byte[] bs = EngineSocketNIO.POOL_BYTE.lend();


            //进行socket获取socketChannel的判断，如果存在协议，就调用协议处理器
            //如果不存在，就另行处理
            String protocol;
//            System.out.println("协议存储 = " + engineSocket.getSocketMap());
            if (EngineSocketNIO.SOCKET_MAP.containsKey(selectionKey)) {
                protocol = EngineSocketNIO.SOCKET_MAP.get(selectionKey);
            } else {
                protocol = ProtocolDispatcher.dispatcher(buffer, bs);
            }

            ProtocolReadHandler protocolReadHandler =
                    EngineSocketNIO.protocolReadFactory.get(protocol);
            if (protocolReadHandler == null) {
                EngineSocketNIO.POOL_BYTE.repay(bs);
                EngineSocketNIO.POOL_BUFFER.repay(buffer);
                UtilSelectionKey.cancel(selectionKey);
                return;
            }

            Map<String, Object> map = EngineSocketNIO.POOL_MAP.lend();
            map.put("selectionKey", selectionKey);
            if (!protocolReadHandler.handle(map, buffer, bs)) {
                EngineSocketNIO.POOL_BYTE.repay(bs);
                EngineSocketNIO.POOL_BUFFER.repay(buffer);
                selectionKey.channel().close();
                return;
            }
            if (!map.containsKey(CoreType.close.toString())) {
                EngineSocketNIO.SOCKET_MAP.put(selectionKey, protocol.replace("UPGRADE", ""));
                log.dateInfo(LogType.time, this, "<SocketChannel,String>的数量 = " + EngineSocketNIO.SOCKET_MAP.size());
            }
            EngineSocketNIO.POOL_BYTE.repay(bs);
            EngineSocketNIO.POOL_BUFFER.repay(buffer);
            log.dateInfo(LogType.time, this, "解析协议结束");
            if (map.containsKey(CoreType.stop.toString())) {
                log.dateInfo(LogType.time, this, "没有进入业务流程，直接返回了");

                return;
            }
            //存放异步标识
            long applicationId = EngineSocketNIO.COUNT_STORE.put(selectionKey);
            map.put(CoreType.applicationId.toString(),
                    String.valueOf(applicationId));
            EngineSocketNIO.engineHandle.push(map);

        } catch (Exception e) {
            log.error(e, "read线程异常");
        }

        log.dateInfo(LogType.time, this, "push数据到handler线程了");

    }


}
