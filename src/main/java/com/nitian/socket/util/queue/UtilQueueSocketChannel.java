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
public class UtilQueueSocketChannel extends UtilQueue<SelectionKey> {


    private EngineSocketNIO engineSocket;
    protected LogManager log = LogManager.getInstance();

    public UtilQueueSocketChannel(EngineSocketNIO engineSocket) {
        // TODO Auto-generated constructor stub
        this.engineSocket = engineSocket;
    }

    @Override
    public synchronized void handle(SelectionKey selectionKey) {
        // TODO Auto-generated method stub
        log.dateInfo(LogType.time, this, "第二步：开始解析SocketChannel中的http或者websocket数据");

        try {

            ByteBuffer buffer = read(selectionKey);
            if (buffer == null) {
                return;
            }
            byte[] bs = EngineSocketNIO.POOL_BYTE.lend();


            //进行socket获取socketChannel的判断，如果存在协议，就调用协议处理器
            //如果不存在，就另行处理
            String protocol;
//            System.out.println("协议存储 = " + engineSocket.getSocketMap());
            if (engineSocket.SOCKET_MAP.containsKey(selectionKey)) {
                protocol = engineSocket.SOCKET_MAP.get(selectionKey).toString();
            } else {
                protocol = ProtocolDispatcher.dispatcher(buffer, bs);
            }

            ProtocolReadHandler protocolReadHandler = engineSocket.protocolReadFactory.get(protocol);
            if (protocolReadHandler == null) {
                EngineSocketNIO.POOL_BYTE.repay(bs);
                EngineSocketNIO.POOL_BUFFER.repay(buffer);
                UtilSelectionKey.cancel(selectionKey);
                return;
            }

            Map<String, Object> map = engineSocket.POOL_MAP.lend();
            if (!protocolReadHandler.handle(map, buffer, bs)) {
                EngineSocketNIO.POOL_BYTE.repay(bs);
                EngineSocketNIO.POOL_BUFFER.repay(buffer);
                selectionKey.channel().close();
                return;
            }
            if (!map.containsKey(CoreType.close.toString())) {
                engineSocket.SOCKET_MAP.put(selectionKey, protocol.replace("UPGRADE", ""));
                log.dateInfo(LogType.time, this, "<SocketChannel,String>的数量 = " + engineSocket.SOCKET_MAP.size());
            }
            EngineSocketNIO.POOL_BYTE.repay(bs);
            EngineSocketNIO.POOL_BUFFER.repay(buffer);
            log.dateInfo(LogType.time, this, "解析协议结束");
            if (map.containsKey(CoreType.stop.toString())) {
                log.dateInfo(LogType.time, this, "没有进入业务流程，直接返回了");

                return;
            }
            //存放异步标识
            long applicationId = engineSocket.COUNT_STORE.put(selectionKey);
            map.put(CoreType.applicationId.toString(),
                    String.valueOf(applicationId));
            engineSocket.engineHandle.push(map);

        } catch (Exception e) {
            log.error(e, "read线程异常");
        }

        log.dateInfo(LogType.time, this, "push数据到handler线程了");

    }


    public synchronized ByteBuffer read(SelectionKey key) throws IOException {
        //首先，借取资源

        ByteBuffer buffer = EngineSocketNIO.POOL_BUFFER.lend();

        SocketChannel socketChannel = (SocketChannel) key.channel();


        int size;

        try {
            size = socketChannel.read(buffer);
        } catch (IOException e) {
            UtilSelectionKey.cancel(key);
            log.error(e, "远程客户端关闭了");
            EngineSocketNIO.POOL_BUFFER.repay(buffer);
            return null;
        }


        if (size > 0) {
            return buffer;
//            buffer.flip();
//            buffer.get(bs, 0, size);
        } else if (size == 0) {
            EngineSocketNIO.POOL_BUFFER.repay(buffer);
            log.dateInfo(LogType.time, this, "读取的数据长度为0，需要释放key和其他资源");
            return null;
        } else if (size == -1) {
            UtilSelectionKey.cancel(key);
            EngineSocketNIO.POOL_BUFFER.repay(buffer);
            log.dateInfo(LogType.time, this, "读取的数据长度为-1，需要释放key和其他资源");
            return null;
        }

        //偿还资源

//        return new String(bs, 0, size, "UTF-8");

        return null;
    }

}
