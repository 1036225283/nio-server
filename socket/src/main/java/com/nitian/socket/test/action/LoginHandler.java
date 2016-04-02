package com.nitian.socket.test.action;

import java.util.Map;

import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.util.json.UtilJson;

public class LoginHandler extends Handler {

	@Override
	public void handle(Map<String, String> map) {
		// TODO Auto-generated method stub
		String result = UtilJson.objectToString(map);
		map.put(CoreType.result.toString(), result);
	}

}
