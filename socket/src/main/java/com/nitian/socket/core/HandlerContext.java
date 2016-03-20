package com.nitian.socket.core;

import java.util.Map;

public class HandlerContext {

	private Map<String, Object> map;

	public Object get(String key) {
		return map.get(key);
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public void initValue() {
		map = null;
	}

}
