package com.nitian.socket.util.websocket;

import com.nitian.util.encrypt.UtilBase64;
import com.nitian.util.encrypt.UtilMd5;

public class UtilWebSocket {

	public static String getSecWebSocketAccept(String SecWebSocketKey) {
		SecWebSocketKey = SecWebSocketKey
				+ "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
		byte[] bs = UtilMd5.stringToSHA1_(SecWebSocketKey);
		String value = UtilBase64.encode(bs);
		System.out.println("web-socket-accept:" + value);
		return value;
	}
}
