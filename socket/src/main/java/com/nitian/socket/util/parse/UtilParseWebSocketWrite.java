package com.nitian.socket.util.parse;

import java.util.Date;
import java.util.Map;

import com.nitian.socket.core.CoreType;

public class UtilParseWebSocketWrite {

	private Map<String, String> map;

	public UtilParseWebSocketWrite(Map<String, String> map) {
		// TODO Auto-generated constructor stub
		this.map = map;
	}

	public byte[] getResult() {
		map.get(CoreType.sessionId.toString());
		StringBuffer sb = new StringBuffer();
		sb.append("HTTP/1.1 101 Switching Protocols").append("\r\n");
		sb.append("Server: Apache-Coyote/1.1").append("\r\n");
		sb.append("Upgrade: websocket").append("\r\n");
		sb.append("Connection: upgrade").append("\r\n");
		String secWebSocketAccept = map.get(CoreType.web_socket_accept
				.toString());
		sb.append("Sec-WebSocket-Accept: ").append(secWebSocketAccept)
				.append("\r\n");
		sb.append("Sec-WebSocket-Extensions: permessage-deflate")
				.append("\r\n");
		sb.append("Date: ").append(new Date().toString()).append("\r\n")
				.append("\r\n");
		return sb.toString().getBytes();
	}
}
