package com.nitian.socket.core;

import java.util.Map;

public class Message {

	private Map<String, String> map;

	public Message(Map<String, String> map) {
		// TODO Auto-generated constructor stub
		this.map = map;
	}

	public String get(String key) {
		return map.get(key);
	}

	/**
	 * 放置返回结果
	 * 
	 * @param result
	 */
	public void putResult(String result) {
		map.put("result", result);
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public void initValue() {
		map = null;
	}

}
