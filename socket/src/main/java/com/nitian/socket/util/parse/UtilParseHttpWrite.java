package com.nitian.socket.util.parse;

import java.util.Date;
import java.util.Map;

import com.nitian.socket.core.CoreType;

public class UtilParseHttpWrite {

	private Map<String, String> map;

	public UtilParseHttpWrite(Map<String, String> map) {
		// TODO Auto-generated constructor stub
		this.map = map;
	}

	public byte[] getResult() {
		map.get(CoreType.sessionId.toString());
		StringBuffer sb = new StringBuffer();
		sb.append("HTTP/1.1 200 OK").append("\r\n");
		sb.append("Server: Apache-Coyote/1.1").append("\r\n");
		sb.append("Content-Type: text/html;charset=UTF-8").append("\r\n");
		String result = "我爱你：{username:xws}";
		sb.append("Content-Length: ").append(result.length()).append("\r\n");
		sb.append("Date: ").append(new Date().toString()).append("\r\n")
				.append("\r\n");
		// ---------------------------------------------------------------------------------------
		sb.append(result);
		return sb.toString().getBytes();
	}
}
