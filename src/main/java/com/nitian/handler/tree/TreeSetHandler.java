package com.nitian.handler.tree;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.nitian.handler.UtilResult;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;
import com.nitian.util.column.tree.Tree;
import com.nitian.util.column.tree.demo1.AVLTree;

public class TreeSetHandler extends Handler {

	private static Tree<Integer, Integer> tree = new Tree<>();

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
		tree.put(Integer.valueOf(key), Integer.valueOf(value));

		tree.eachLeft();

		map.put(CoreType.result.toString(), JSON.toJSON(tree).toString());
	}

}
