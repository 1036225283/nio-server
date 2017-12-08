package com.nitian.socket.util.protocol.ssl;

import com.nitian.socket.EngineSocketNIO;
import com.nitian.socket.core.CoreProtocol;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.UtilSession;
import com.nitian.socket.util.key.UtilSelectionKey;
import com.nitian.socket.util.protocol.read.ProtocolReadHandler;
import com.nitian.util.java.ByteList;
import com.nitian.util.log.LogManager;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.util.Map;

/**
 * HTTPS协议解析器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolHttpsReadHandler extends ProtocolReadHandler {

    protected static LogManager log = LogManager.getInstance();

    public static int ClientHello = 1;
    public static int ClientKeyExchange = 16;
    public static int ServerHello = 2;


    //get action
    public static int getAction(byte[] bs) {
        return bs[5];
    }


    @Override
    public void handle(Map<String, Object> map, ByteBuffer buffer, byte[] bs) {

        SelectionKey selectionKey = null;
        long applicationId = 0;

        try {

            buffer.flip();
            int length = buffer.remaining();
            buffer.get(bs, 0, length);
            //先判断是什么协议
            int nHandshakeType = SSL.getHandshakeProtocol(bs);
            //如果是握手协议
            if (nHandshakeType == SSL.SSLHandshake) {
                int action = getAction(bs);
                if (action == ClientHello) {
                    //解析clientHello
                    SSLClientHello hello = SSLClientHelloHandler.handler(bs);


                    String strSessionId = UtilSession.createSessionId();
                    Map<String, Object> session = UtilSession.get(strSessionId);
                    session.put("clientHello", hello);
                    UtilSession.updateTime(strSessionId);

                    selectionKey = (SelectionKey) map.get("selectionKey");
                    //存放异步标识
                    applicationId = EngineSocketNIO.COUNT_STORE.put(selectionKey);
                    map.put(CoreType.applicationId.toString(),
                            String.valueOf(applicationId));

                    map.put(CoreType.sessionId.toString(), strSessionId);

                    EngineSocketNIO.POOL_BYTE.repay(bs);
                    EngineSocketNIO.POOL_BUFFER.repay(buffer);

                    map.put("action",action);

                    EngineSocketNIO.QUEUE_WRITE.push(map);


                } else if (nHandshakeType == SSL.SSHApplicationData) {

                }

                map.put(CoreType.protocol.toString(), CoreProtocol.HTTPS.toString());
            }
        } catch (Exception e) {
            UtilSelectionKey.cancel(selectionKey);
            EngineSocketNIO.COUNT_STORE.remove(applicationId);
            log.error(e, "解析HTTPS协议出错了!!!");
        }

    }


}