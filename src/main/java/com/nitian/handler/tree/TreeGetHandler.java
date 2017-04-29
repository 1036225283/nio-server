package com.nitian.handler.tree;

import java.util.Map;

import com.nitian.handler.UtilResult;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;
import com.nitian.util.column.tree.Node;
import com.nitian.util.column.tree.Tree;

/**
 * get(tree)
 */
public class TreeGetHandler extends Handler {

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

		Tree<Integer, Integer> tree = TreeFactory.getSelfAvl();
		long startTime = System.nanoTime();
		Node<Integer, Integer> node = tree.get(Integer.valueOf(key));
		long endTime = System.nanoTime();

		String value = "value not find";
		if (node != null) {
			value = node.getValue() + "";
		}
		map.put(CoreType.result.toString(), UtilResult.success(key, value, endTime - startTime));

	}

}
