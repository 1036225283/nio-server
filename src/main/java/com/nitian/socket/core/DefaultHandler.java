package com.nitian.socket.core;

import java.util.Map;

public class DefaultHandler extends Handler {

	@Override
	public void handle(Map<String, Object> map) {
		// TODO Auto-generated method stub
		map.put(CoreType.result.toString(), "{\"state\":\"404\"}");
	}

}
