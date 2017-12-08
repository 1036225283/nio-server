package com.nitian.socket.util.protocol.ssl;

import com.nitian.util.java.ByteList;

/**
 * ssl server hello
 * Created by xws on 12/4/17.
 */
public class SSLServerHello {

    private int version;
    private byte[] sessionId;
//    private

    //构造serverHello
    public byte[] createServerHello() {
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

        return null;
    }

    //create certificate
    public byte[] createCertificate() {
        ByteList byteList = new ByteList();
        byteList.add((byte) 22);//content type handshake(22)
        byteList.add((byte) 3);//version tls(0x0303)
        byteList.add((byte) 3);
        byteList.add((byte) 0);//length
        byteList.add((byte) 0);
        byteList.add((byte) 11);//handshake protocol:Certificate

        byteList.add((byte) 11);//handshake protocol length
        byteList.add((byte) 0);
        byteList.add((byte) 0);


        byteList.add((byte) 0);//Certificates length
        byteList.add((byte) 0);
        byteList.add((byte) 0);


        byteList.add((byte) 0);//Certificates length
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

        return null;
    }



}
