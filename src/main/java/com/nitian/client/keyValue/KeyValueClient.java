package com.nitian.client.keyValue;

import com.alibaba.fastjson.JSON;
import com.nitian.socket.core.CoreType;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;

/**
 * key-value系统的客户端，单线程，
 */
public class KeyValueClient {

	private Socket socket;
	private String ip;
	private int port;
	private byte[] bytes = new byte[1024 * 8];

	public KeyValueClient setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public KeyValueClient setPort(int port) {
		this.port = port;
		return this;
	}

	public KeyValueClient connection() {
		try {
			InetAddress inetAddress = InetAddress.getByName(ip);
			socket = new Socket(inetAddress.getHostAddress(), port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public void close() {
		try {
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void set(String key, String value) {
		String writeString = UtilProtocol.write("key-value/set", key + "=" + value, ip, port);
		this.write(writeString.getBytes());
		String message = this.read();
		Map<String, String> map = UtilProtocol.read(message);
		System.out.println(map);
	}

	public String get(String key) {

		String writeString = UtilProtocol.write("/key-value/get", "key=" + UtilProtocol.encode(key), ip, port);
		this.write(writeString.getBytes());
		String message = this.read();
		Map<String, String> map = UtilProtocol.read(message);
		String param = map.get(CoreType.param.toString());
		Map<String, String> map1 = (Map) JSON.parse(param);
		return map1.get("value");

	}

	public void remove(String key) {

	}

	private void write(byte[] bytes) {
		try {
			OutputStream out = socket.getOutputStream();
			out.write(bytes);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String read() {
		try {
			int size = socket.getInputStream().read(bytes);
			return new String(bytes, 0, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		KeyValueClient client = new KeyValueClient("www.1036225283.com", 88);

		for (int i = 0; i < 1000; i++) {
			long start = System.nanoTime();
			String value = client.get("" + i);

			long end = System.nanoTime();
		}
	}

	public KeyValueClient(String ip, int port) throws IOException {
		// TODO Auto-generated constructor stub
		this.setIp(ip).setPort(port).connection();
	}

}
