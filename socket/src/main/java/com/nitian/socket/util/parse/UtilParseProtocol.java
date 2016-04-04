package com.nitian.socket.util.parse;

import java.util.Map;

import com.nitian.socket.core.CoreType;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

public class UtilParseProtocol {

	protected LogManager log = LogManager.getInstance();

	private Map<String, String> map;
	private String[] strings;

	public UtilParseProtocol(String request, Map<String, String> map) {
		// TODO Auto-generated constructor stub
		this.map = map;

		strings = request.split("\r\n");

		log.info(LogType.debug, this, "----原生数据=" + request);
		log.info(LogType.debug, this, "----headers=" + strings.length);

		String protocol = UtilParseRead.getProtocol(strings);

		if (protocol.equals("HTTP")) {
			this.map.put(CoreType.protocol.toString(), "HTTP");
			UtilParseHttpRead.parse(map, strings);
		} else if (protocol.equals("WEBSOCKET")) {
			this.map.put(CoreType.protocol.toString(), "WEBSOCKET");
			UtilParseWebSocketRead.parse(map, strings);
		}
	}

}
