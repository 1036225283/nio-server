package com.nitian.socket.util.protocol.ssl;

import com.nitian.util.java.UtilByte;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ssl client hello
 * Created by xws on 6/25/17.
 */
public class SSLClientHello {

    // bs[0]:(22):   change_cipher_spec(20) alert(21) handshake(22) application_data(23)
    // bs[1] bs[2]:(0x0301):version
    // bs[3] bs[4]:(0x0b06): data length
    // bs[5]:(0x01): client hello
    // bs[6]-bs[9]:time
    // bs[10]-bs[42]:random
    // bs[43]-bs[45]:session id
    // bs[46]+68:cipher
    public static void test(byte[] bs,int length) {

        System.out.println(UtilByte.toHex(bs,length));

        int bHandshakeContentType = bs[0];
        int nHandshakeVersion = SSL.getHandshakeVersion(bs);
        int nHandshakeLength = SSL.getHandshakeLength(bs);
        int bClientHello = bs[6];
        int ch1 = bs[1];
        int ch2 = bs[2];
        int ch3 = bs[3];
        int ch4 = bs[4];

        if (bHandshakeContentType == 22) {
            System.out.println("this is handshake");
        }

        System.out.println(UtilByte.toHex((byte) ch1));
        System.out.println(UtilByte.toHex((byte) ch2));
        System.out.println(UtilByte.toHex((byte) ch3));
        System.out.println(UtilByte.toHex((byte) ch4));


        if (bs[5] == 1) {
            System.out.println("client hello");

        }
        //get time
        long ch6 = bs[6];
        long ch7 = bs[7];
        long ch8 = bs[8];
        long ch9 = bs[9];
        long second = (ch6 << 24) + (ch7 << 16) + (ch8 << 8) + ch9;
        Date date = new Date(second);
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(dateFormater.format(date));
        //get session id
        int ch43 = bs[43];
        System.out.println("[43] : "+UtilByte.toHex((byte) ch43));

        if (ch43 == 0) {
            System.out.println("session id is empty");
            String cipher = new String(bs, 46, 68);
            System.out.println(cipher);
        }

        String cipher = new String(bs, length-68, 68);
        System.out.println(cipher);



    }

}
