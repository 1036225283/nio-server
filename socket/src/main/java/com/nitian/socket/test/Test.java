package com.nitian.socket.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.nitian.socket.util.pool.UtilPoolByte;
import com.nitian.socket.util.pool.UtilPoolMap;
import com.nitian.socket.util.queue.UtilQueueRead;
import com.nitian.socket.util.queue.UtilQueueParse;
import com.nitian.socket.util.websocket.UtilWebSocket;
import com.nitian.util.encrypt.UtilBase64;
import com.nitian.util.encrypt.UtilMd5;
import com.nitian.util.java.UtilByte;
import com.nitian.util.log.LogManager;
import com.nitian.util.time.UtilTime;
import com.thoughtworks.xstream.core.util.Base64Encoder;

public class Test {

	public void show() {
		System.out.println("this is show");
	}

	public static void main(String[] args) throws IOException,
			InstantiationException, IllegalAccessException,
			NoSuchAlgorithmException {
		testGetBit();
	}

	public static void testGetBit() {
		byte height = (byte) 255;
		for (int i = 8; i > 0; i--) {
			System.out.print(UtilByte.getBit(height, i));
		}
		System.out.println();
		System.out.println(UtilByte.toBin(height));
	}

	public static void testWebSocketAccept() {
		System.out.println(UtilWebSocket
				.getSecWebSocketAccept("ch3uUUC+Yu05yCNTw0sBBQ=="));

	}

	public static void testBase64(String tingting)
			throws UnsupportedEncodingException {
		UtilBase64 base64 = new UtilBase64();
		System.out.println(tingting.length());
		String encode = base64.encode(tingting);
		System.out.println(encode);
	}

	public static void testSHA() {
		String string = "ibAzXMSXFwTYt6lZeKh7Zw==";
		string = string + "258EAFA5-E914-47DA- 95CA-C5AB0DC85B11";
		byte[] bs = UtilMd5.stringToSHA1_(string);
		String result = new Base64Encoder().encode(bs);
		System.out.println(result);
	}

	public void testClass() throws InstantiationException,
			IllegalAccessException {
		Class<?> c = new Test().getClass();
		Test t = (Test) c.newInstance();
		t.show();
	}

	public static void testQueueRead() {
		UtilQueueRead queueRead = new UtilQueueRead(null);
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
