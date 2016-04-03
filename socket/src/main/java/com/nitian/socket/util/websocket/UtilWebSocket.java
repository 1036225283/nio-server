package com.nitian.socket.util.websocket;

import com.nitian.util.encrypt.UtilMd5;
import com.thoughtworks.xstream.core.util.Base64Encoder;

public class UtilWebSocket {

	public static void main(String[] args) {
		System.out.println(getSecWebSocketAccept("ibAzXMSXFwTYt6lZeKh7Zw=="));
	}

	public static String getSecWebSocketAccept(String SecWebSocketKey) {
		SecWebSocketKey = SecWebSocketKey
				+ "258EAFA5-E914-47DA- 95CA-C5AB0DC85B11";
		byte[] bs = UtilMd5.stringToSHA1_(SecWebSocketKey);
		String value = new Base64Encoder().encode(bs);
		return value;
	}
}
