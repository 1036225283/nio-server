package com.nitian.socket.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Test {
	public static void main(String[] args) throws IOException {
		String test = "abc ddd 23   1";
		String[] strings = test.split(" ");
		for (int i = 0; i < strings.length; i++) {
			System.out.println(strings[i].equals(""));
		}
		System.out.println(strings.length);
	}

	public static void testStringIndexOf() {
		String test = "aabc32234";
		System.out.println(test.indexOf("abc"));
	}

	public static void testInputStream() throws IOException {
		File file = new File("C:/Users/1036225283/Desktop/test/test.txt");
		// file.get
		InputStream inputStream = new FileInputStream(file);
		byte[] bs = new byte[1];
		// for (int i = 0; i < 101; i++) {
		// byte b = (byte) inputStream.read();
		// System.out.println(b);
		// bs[0] = b;
		// System.out.println(UtilStringHex.bytesHexStr(bs));
		// }

		byte[] bs2 = new byte[19];
		int index = inputStream.read(bs2);
		System.out.println(index);
	}

	public static void testPoolByte() {
		UtilPoolByte poolByte = new UtilPoolByte(20, 2, null);
		byte[] bs = poolByte.lend();
		System.out.println(bs.length);
	}

	public static void testPoolMap() {
		UtilPoolMap poolMap = new UtilPoolMap(100, 1);

		for (int i = 0; i < 10; i++) {
			System.out.println("poolSize : " + poolMap.getTotal());
			poolMap.lend();
		}

		Map<String, Object> m = poolMap.lend();
		System.out.println(m);
		m.put("hello", "world");
		System.out.println(m.get("hello"));
	}

	public static void testList() {
		List<String> list = new LinkedList<String>();
		list.add("aaaa");
		list.add("bbbb");
		String c = list.remove(0);
		System.out.println(c);
		System.out.println(list.size());
	}
}
