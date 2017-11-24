package com.nitian.socket.util.protocol.ssl;

import com.nitian.util.java.UtilByte;
import com.nitian.util.time.UtilTime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ssl
 * Created by xws on 6/25/17.
 */
public class SSL {

    // bs[0]:(22):   change_cipher_spec(20) alert(21) handshake(22) application_data(23)
    // bs[1] bs[2]:(0x0301):version
    // bs[3] bs[4]:(0x0b06): data length
    // bs[5]:(0x01): client hello
    // bs[6]-bs[9]:time
    // bs[10]-bs[42]:random
    // bs[43]-bs[45]:session id
    // bs[46]+68:cipher
    public static void test(byte[] bs, int length) {

        System.out.println(UtilByte.toHex(bs, length));

        int ch0 = bs[0];
        int ch1 = bs[1];
        int ch2 = bs[2];
        int ch3 = bs[3];
        int ch4 = bs[4];

        if (ch0 == 22) {
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
        System.out.println("[43] : " + UtilByte.toHex((byte) ch43));

        if (ch43 == 0) {
            System.out.println("session id is empty");
            String cipher = new String(bs, 46, 68);
            System.out.println(cipher);
        }

        String cipher = new String(bs, length - 68, 68);
        System.out.println(cipher);


    }


    //get handshake version
    public static int getHandshakeVersion(byte[] bs) {
        int high = bs[1];
        int low = bs[2];
        return (high << 8) + low;
    }

    //get handshake data length
    public static int getHandshakeLength(byte[] bs) {
        int high = bs[4];
        int low = bs[5];
        return (high << 8) + low;
    }

    //get client hello length
    public static long getClientHelloLength(byte[] bs) {
        long bit1 = bs[7];
        long bit2 = bs[8];
        long bit3 = bs[9];
        return (bit1 << 16) + (bit2 << 8) + bit3;
    }

    //get client hello version
    public static int getClientHelloVersion(byte[] bs) {
        int high = bs[10];
        int low = bs[11];
        return (high << 8) + low;
    }

    //get client hello random time
    public static String getClientHelloRandomTime(byte[] bs) {
        long bit1 = bs[12];
        long bit2 = bs[13];
        long bit3 = bs[14];
        long bit4 = bs[15];
        long time = (bit1 << 24) + (bit2 << 16) + (bit3 << 8) + bit4;

        System.out.println(UtilTime.dateToyyyyMMddHHmmss(new Date(time)));
        return UtilTime.dateToyyyyMMddHHmmss(new Date(time));
    }


    //get client hello random
    public static String getClientHelloRandom(byte[] bs) {
        return new String(bs, 16, 28);
    }
}
