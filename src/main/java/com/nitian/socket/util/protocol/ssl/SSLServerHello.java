package com.nitian.socket.util.protocol.ssl;

import com.nitian.util.encrypt.UtilRSA;
import com.nitian.util.java.ByteList;
import com.nitian.util.java.UtilByte;

import java.security.cert.Certificate;

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

    //TLSv1.2 Record Layer: Handshake Protocol: Certificate
    public byte[] createCertificate() throws Exception {

        UtilRSA utilRSA = new UtilRSA("/software/www.1036225283.com.jks", "890512", "www.1036225283.com", "890512");
        Certificate[] certificates = utilRSA.getCertificates();


        ByteList byteList = new ByteList();

        //1 Content Type: Handshake (22)
        byteList.add((byte) 22);

        //2 Version: TLS 1.2 (0x0303)
        byteList.add((byte) 3);
        byteList.add((byte) 3);

        //2 length
        byteList.add((byte) 0);
        byteList.add((byte) 0);

        //Handshake Protocol: Certificate
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
        byteList.set(12, bsTotal);
        byte[] bsHandshake = UtilByte.intToBytes3(certificatesLength + 3);
        byteList.set(9, bsHandshake);
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

}
