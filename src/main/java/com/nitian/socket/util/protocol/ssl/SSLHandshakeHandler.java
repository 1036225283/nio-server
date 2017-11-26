package com.nitian.socket.util.protocol.ssl;

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
            SSLClientHello hello = SSLClientHelloHandler.handler(bs);
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
