package com.nitian.handler.keyValue;

import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;

import java.util.Map;

public class InitHandler extends Handler {

	private static KeyValue keyValue = KeyValue.getInstance();

	@Override
	public void handle(Map<String, Object> map) {
		// TODO Auto-generated method stub

		for (int i = 0; i < 800000; i++) {
			keyValue.set(i + "", i + "");
		}

		map.put(CoreType.result.toString(), "init is end");
	}

}
