package com.nitian.socket.util.protocol.ssl;

import com.nitian.util.java.UtilByte;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ssl client hello
 * Created by xws on 6/25/17.
 */
public class SSLClientHelloHandler {

    // bs[0]:(22):   change_cipher_spec(20) alert(21) handshake(22) application_data(23)
    // bs[1] bs[2]:(0x0301):version
    // bs[3] bs[4]:(0x0b06): data length
    // bs[5]:(0x01): client hello
    // bs[6]-bs[9]:time
    // bs[10]-bs[42]:random
    // bs[43]-bs[45]:session id
    // bs[46]+68:cipher

    public int nHandshakeProtocol;
    public int nHandshakeProtocolVersion;
    public int nHandshakeType;
    public int nHandshakeVersion;
    public String strHandshakeRandomTime;
    public String strHandshakeRandom;


    public SSLClientHelloHandler(byte[] bs) {
        nHandshakeProtocol = getHandshakeProtocol(bs);
        nHandshakeProtocolVersion = getHandshakeProtocolVersion(bs);
        nHandshakeType = getHandshakeType(bs);
        nHandshakeVersion = getClientHelloVersion(bs);
        strHandshakeRandomTime = getClientHelloRandomTime(bs);
    }

    //处理client hello
    public static SSLClientHello handler(byte[] bs) {
        SSLClientHello hello = new SSLClientHello();

        String strRandomTime = getClientHelloRandomTime(bs);
        hello.setTime(strRandomTime);

        byte[] bsRandom = getClientHelloRandom(bs);
        hello.setRandom(bsRandom);
        return hello;
    }

    //get handshake protocol
    public int getHandshakeProtocol(byte[] bs) {
        return bs[0];
    }

    //get handshake protocol version
    public int getHandshakeProtocolVersion(byte[] bs) {
        int high = bs[1];
        int low = bs[2];
        return (high << 8) + low;
    }

    //get handshake data length
    public int getHandshakeProtocolLength(byte[] bs) {
        int high = bs[4];
        int low = bs[5];
        return (high << 8) + low;
    }


    //get handshake type
    public int getHandshakeType(byte[] bs) {
        return bs[8];
    }


    //get client hello length
    public long getClientHelloLength(byte[] bs) {
        long bit1 = bs[7];
        long bit2 = bs[8];
        long bit3 = bs[9];
        return (bit1 << 16) + (bit2 << 8) + bit3;
    }

    //get client hello version
    public int getClientHelloVersion(byte[] bs) {
        int high = bs[10];
        int low = bs[11];
        return (high << 8) + low;
    }

    //get client hello random time
    public static String getClientHelloRandomTime(byte[] bs) {
//        long bit1 = bs[11]& 0xFF;
//        long bit2 = bs[12]& 0xFF;
//        long bit3 = bs[13]& 0xFF;
//        long bit4 = bs[14]& 0xFF;
//        long time = (bit1 << 24) | (bit2 << 16) | (bit3 << 8) | bit4;
        long time = UtilByte.byte4ToLong(bs, 11);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = simpleDateFormat.format(new Date(time * 1000));
        System.out.println(strTime);
        return strTime;
    }


    //get client hello random
    public static byte[] getClientHelloRandom(byte[] bs) {
        byte[] bs1 = new byte[28];
        UtilByte.copy(bs, 14, bs1, 0);
        return bs1;
    }
}
