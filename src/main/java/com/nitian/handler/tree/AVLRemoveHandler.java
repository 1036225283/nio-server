package com.nitian.handler.tree;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.nitian.handler.UtilResult;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;

public class AVLRemoveHandler extends Handler {

	@Override
	public void handle(Map<String, String> map) {
		// TODO Auto-generated method stub

		String param = map.get(CoreType.param.toString());
		Map<String, String> paramMap = UtilParam.getParam(param);

		String key = paramMap.get("key");
		if (key == null) {
			map.put(CoreType.result.toString(), UtilResult.keyIsNull("key is null"));
			return;
		}
		String value = paramMap.get("value");
		if (value == null) {
			map.put(CoreType.result.toString(), UtilResult.keyIsNull("value is null"));
			return;
		}
		TreeFactory.getAVL().remove(Integer.valueOf(key));
		map.put(CoreType.result.toString(), JSON.toJSON(TreeFactory.getAVL()).toString());
	}

}
