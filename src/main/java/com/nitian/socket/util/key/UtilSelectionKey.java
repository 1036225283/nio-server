package com.nitian.socket.util.key;

import com._1036225283.util.self.log.LogManager;
import com._1036225283.util.self.log.LogType;
import com.nitian.socket.EngineSocketNIO;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * nio 异常时关闭SelectionKey和SocketChannel
 * Created by 1036225283 on 2016/12/20.
 */
public class UtilSelectionKey {

    protected static LogManager log = LogManager.getInstance();

    public static void cancel(SelectionKey selectionKey) {
        selectionKey.cancel();
        try {
            selectionKey.channel().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static synchronized ByteBuffer read(SelectionKey key) throws IOException {
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
            log.dateInfo(LogType.time, log, "读取的数据长度为0，需要释放key和其他资源");
            return null;
        } else if (size == -1) {
            UtilSelectionKey.cancel(key);
            EngineSocketNIO.POOL_BUFFER.repay(buffer);
            log.dateInfo(LogType.time, log, "读取的数据长度为-1，需要释放key和其他资源");
            return null;
        }

        return null;
    }

}
