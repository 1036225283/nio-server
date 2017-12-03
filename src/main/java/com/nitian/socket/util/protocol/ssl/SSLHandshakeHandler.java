package com.nitian.socket.util.protocol.ssl;

import com.nitian.socket.util.UtilSession;
import com.nitian.util.java.ByteList;

import java.util.Map;

/**
 * ssl handshake handler
 * Created by xws on 11/25/17.
 */
public class SSLHandshakeHandler {


    public static int ClientHello = 1;
    public static int ClientKeyExchange = 16;
    public static int ServerHello = 2;


    public static void hander(byte[] bs) {

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
            Map<String, Object> map = UtilSession.get(strSessionId);
            map.put("clientHello", hello);
            UtilSession.updateTime(strSessionId);


        } else if (action == ClientKeyExchange) {

        } else if (action == ServerHello) {

        } else {

        }

    }

    //get action
    public static int getAction(byte[] bs) {
        return bs[5];
    }


}
