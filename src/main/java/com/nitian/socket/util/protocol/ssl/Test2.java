package com.nitian.socket.util.protocol.ssl;

import com.nitian.util.java.ByteList;
import com.nitian.util.java.UtilByte;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xws on 12/13/17.
 */
public class Test2 {

    public static void main(String[] args) throws ParseException {
        test0();

    }


    public static void test0() {

        ByteList byteList = new ByteList();

        byteList.add((byte) 0);
        byteList.add((byte) 0);
        byteList.add((byte) 0);
        byteList.add((byte) 0);

        byteList.add((byte) 0x86);
        byteList.add((byte) 0xdf);
        byteList.add((byte) 0x37);
        byteList.add((byte) 0x34);

        long b = 0x86df3734;
        long d = 0x1;
        long a = 2262775604l;
        byte[] bsa = UtilByte.longToByte4(a);
        System.out.println(UtilByte.toHex(bsa));
        System.out.println(UtilByte.toHex(byteList.getByte()));


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = simpleDateFormat.format(new Date(a * 1000));
        System.out.println(strTime);

        System.out.println(new Date(a * 1000).getTime());


        d = UtilByte.byte4ToLong(bsa, 0);

        System.out.println(d);
        System.out.println(UtilByte.byte4ToLong(byteList.getByte(), 4));

    }

    public static void test1() {
        long b = 0x86df3734;
        byte[] bsa = UtilByte.longToBytes(2262775604l);
        long aaa = UtilByte.bytesToLong(bsa);


        System.out.println(UtilByte.toHex(bsa));
        System.out.println(aaa);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = simpleDateFormat.format(new Date(aaa * 1000));

        System.out.println(strTime + "-------------");


    }

}
