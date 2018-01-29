package com.nitian.socket.util.protocol;

/**
 * test
 * Created by xws on 6/25/17.
 */
public class Test {

    public static void main(String[] args) {
        byte[] b = new byte[]{1, 2, 3, 4};
        int a0 = b[0];
        int a1 = b[1];
        int a2 = b[2];
        int a3 = b[3];

//        System.out.println(a0<<4);
        System.out.println((a0<<8)+(a1<<0));
    }
}
