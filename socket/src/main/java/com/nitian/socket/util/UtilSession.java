package com.nitian.socket.util;

import java.util.HashMap;
import java.util.Map;

import com.nitian.util.random.UtilRandom;

public class UtilSession {

	private static Map<String, Map<String, Object>> sessionMap = new HashMap<String, Map<String, Object>>();

	/**
	 * 创建sessionId
	 * 
	 * @return
	 */
	public static String createSessionId() {
		String sessionId = UtilRandom.createUUID();
		while (sessionMap.containsKey(sessionId)) {
			sessionId = UtilRandom.createUUID();
		}
		sessionMap.put(sessionId, new HashMap<String, Object>());
		return sessionId;
	}

}
