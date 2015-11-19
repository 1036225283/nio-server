package com.nitian.test;

import com.nitian.util.java.UtilByte;
import com.nitian.util.random.UtilRandom;
import com.nitian.util.string.UtilStringHex;

public class Test {

	public static void main(String[] args) {
		byte[] data = UtilRandom.createUUID().getBytes();
		byte[] length = UtilByte.intToBytes(data.length);
		byte[] write = new byte[data.length + 4];
		UtilByte.copy(write, length, 0);
		UtilByte.copy(write, data, 4);
		System.out.println(UtilStringHex.bytesHexStr(write));
		
		String write1 = UtilRandom.createHexString(512*8);
		System.out.println(write1.length());
	}

	public static void test() {
		int a = 165536;
		byte[] bs = UtilByte.intToBytes(a);
		System.out.println(UtilStringHex.bytesHexStr(bs));
		a = UtilByte.bytesToInt(bs);
		System.out.println(a);
	}
}
