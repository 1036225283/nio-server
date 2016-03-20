package com.nitian.socket.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.nitian.socket.util.UtilPoolByte;
import com.nitian.socket.util.UtilPoolMap;
import com.nitian.socket.util.UtilQueueRead;

public class Test {
	public static void main(String[] args) throws IOException {
		UtilQueueRead queueRead = new UtilQueueRead();
		queueRead.start();
		for (int i = 0; i < 10000; i++) {
			queueRead.push(new HashMap<String, String>());
		}
	}

	public static void testFileOutPutStream() throws IOException {
		String test = Test.class.getResource("/").getFile().substring(1);
		OutputStream outputStream = new FileOutputStream(
				"C:\\Users\\1036225283\\Desktop\\test\\dd.json");
		outputStream.write("sjkjlkjk".getBytes());
		System.out.println(test);
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

		Map<String, String> m = poolMap.lend();
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
