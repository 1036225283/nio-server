package com.nitian.socket.util.protocol.ssl;

import com.nitian.util.encrypt.UtilRSA;
import com.nitian.util.java.ByteList;
import com.nitian.util.java.UtilByte;
import com.nitian.util.random.UtilRandom;

import java.security.cert.Certificate;
import java.util.Date;

/**
 * ssl server hello
 * Created by xws on 12/4/17.
 */
public class SSLServerHello {

    private int version;
    private byte[] sessionId;
//    private

    public static ByteList createHandshake() {
        ByteList byteList = new ByteList();
        byteList.add((byte) 22);//content type handshake(22)
        byteList.add((byte) 3);//version tls(0x0303)
        byteList.add((byte) 3);
        byteList.add((byte) 0);//length
        byteList.add((byte) 0);
        return byteList;
    }

    //构造serverHello
    public byte[] createServerHello(ByteList byteList) {
        int index = byteList.size();
        System.out.println("serverHello.index = " + index);
        //server hello
        byteList.add((byte) 2);

        //server hello length
        byteList.add((byte) 0);
        byteList.add((byte) 0);
        byteList.add((byte) 0x4d);

        //version tls(0x0303)
        byteList.add((byte) 3);
        byteList.add((byte) 3);

        //random time
        long lTime = new Date().getTime() / 1000;
        byte[] bsTime = UtilByte.longToByte4(lTime);
        byteList.add(bsTime);

        //random byte
        byte[] random = UtilRandom.createRandomByte(28);
        byteList.add(random);

        //session id length
        byteList.add((byte) 32);

        //session id
        byte[] sessionId = UtilRandom.createRandomByte(32);
        byteList.add(sessionId);

        //Cipher Suite: TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 (0xc02f)
        byteList.add((byte) 0xc0);
        byteList.add((byte) 0x2f);

        //Compression Method: null (0)
        byteList.add((byte) 0);

        //Extensions Length: 5
        byteList.add((byte) 5);

        //Type: renegotiation_info (0xff01)
        byteList.add((byte) 0xff);
        byteList.add((byte) 0x01);

        //length
        byteList.add((byte) 0x00);
        byteList.add((byte) 0x01);

        //Renegotiation Info extension
        byteList.add((byte) 0x00);


        return byteList.getByte();
    }

    //TLSv1.2 Record Layer: Handshake Protocol: Certificate
    public byte[] createCertificate(ByteList byteList) throws Exception {

        int index = byteList.size();
        System.out.println("certificate.index = " + index);


        UtilRSA utilRSA = new UtilRSA("/software/www.1036225283.com.jks", "890512", "www.1036225283.com", "890512");
        Certificate[] certificates = utilRSA.getCertificates();


        //1 Handshake Type: Certificate (11)
        byteList.add((byte) 11);

        //3  length
        byteList.add((byte) 11);
        byteList.add((byte) 0);
        byteList.add((byte) 0);


        //3 Certificates length
        byteList.add((byte) 0);
        byteList.add((byte) 0);
        byteList.add((byte) 0);

        ByteList cateList = new ByteList();

        //Certificates Length
        int certificatesLength = 0;
        for (Certificate certificate : certificates) {
            byte[] bs = certificate.getEncoded();
            byte[] length = UtilByte.intToBytes3(bs.length);
            certificatesLength = certificatesLength + bs.length;
            cateList.add(length);
            cateList.add(bs);
        }

        byte[] bsTotal = UtilByte.intToBytes3(certificatesLength);
        byteList.set(index + 12, bsTotal);
        byte[] bsHandshake = UtilByte.intToBytes3(certificatesLength + 3);
        byteList.set(index + 9, bsHandshake);
        byteList.add(cateList.getByte());
        return byteList.getByte();
    }

    public byte[] createServerKeyExchange() {
        ByteList byteList = new ByteList();
        //Content Type: Handshake (22)
        byteList.add((byte) 22);

        //Version: TLS 1.2 (0x0303)
        byteList.add((byte) 3);
        byteList.add((byte) 3);

        //length
        byteList.add((byte) 0);
        byteList.add((byte) 0);

        //Handshake Type: Server Key Exchange (12)
        byteList.add((byte) 12);

        //length
        byteList.add((byte) 0);
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

    public byte[] createServerHelloDone() {
        ByteList byteList = new ByteList();

        //content type handshake(22)
        byteList.add((byte) 22);

        //version tls(0x0303)
        byteList.add((byte) 3);
        byteList.add((byte) 3);

        //length
        byteList.add((byte) 0);
        byteList.add((byte) 4);

        //Handshake Type: Server Hello Done (14)
        byteList.add((byte) 14);


        //length
        byteList.add((byte) 0);
        byteList.add((byte) 0);
        byteList.add((byte) 0);

        return byteList.getByte();
    }

    public byte[] createChangeCipherSpec() {
        ByteList byteList = new ByteList();

        //content type handshake(20)
        byteList.add((byte) 20);

        //version tls(0x0303)
        byteList.add((byte) 3);
        byteList.add((byte) 3);

        //length
        byteList.add((byte) 0);
        byteList.add((byte) 1);

        byteList.add((byte) 1);

        return byteList.getByte();
    }

    public byte[] createHelloRequest() {
        ByteList byteList = new ByteList();

        //content type handshake(22)
        byteList.add((byte) 22);

        //version tls(0x0303)
        byteList.add((byte) 3);
        byteList.add((byte) 3);

        //length
        byteList.add((byte) 0);
        byteList.add((byte) 40);

        byteList.add((byte) 1);

        return byteList.getByte();

    }

}
