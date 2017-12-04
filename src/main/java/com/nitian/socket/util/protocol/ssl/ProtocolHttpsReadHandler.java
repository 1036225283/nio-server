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
                    //构造serverHello
                    ByteList byteList = new ByteList();
                    byteList.add((byte) 22);//content type handshake(22)
                    byteList.add((byte) 3);//version tls(0x0303)
                    byteList.add((byte) 3);
                    byteList.add((byte) 0);//length
                    byteList.add((byte) 0);
                    byteList.add((byte) 2);//server hello

                    byteList.add((byte) 0);//server hello length
                    byteList.add((byte) 0);
                    byteList.add((byte) 0);

                    byteList.add((byte) 3);//version tls(0x0303)
                    byteList.add((byte) 3);

                    byteList.add(new byte[4]);//random time
                    byteList.add(new byte[28]);//random byte

                    byteList.add((byte) 0);//session id length

                    byteList.add((byte) 0);//cipher suite
                    byteList.add((byte) 0);

                    byteList.add((byte) 0);//compression method

                    byteList.add((byte) 0);//extension length
                    byteList.add((byte) 0);


                    String strSessionId = UtilSession.createSessionId();
                    map = UtilSession.get(strSessionId);
                    map.put("clientHello", hello);
                    UtilSession.updateTime(strSessionId);

                    EngineSocketNIO.QUEUE_WRITE.push(map);


                    selectionKey = (SelectionKey) map.get("selectionKey");
                    //存放异步标识
                    applicationId = EngineSocketNIO.COUNT_STORE.put(selectionKey);
                    map.put(CoreType.applicationId.toString(),
                            String.valueOf(applicationId));
                    EngineSocketNIO.POOL_BYTE.repay(bs);
                    EngineSocketNIO.POOL_BUFFER.repay(buffer);
                    EngineSocketNIO.engineHandle.push(map);


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