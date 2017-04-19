package com.nitian.handler.tree;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.nitian.handler.UtilResult;
import com.nitian.handler.redis.Redis;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;
import com.nitian.util.column.tree.Tree;

/**
 * get(tree)
 */
public class TreeGetHandler extends Handler {

	private static Redis redis = Redis.getInstance();
	private static Tree<Integer, Integer> tree = TreeFactory.getInstance();

	@Override
	public void handle(Map<String, String> map) {
		// TODO Auto-generated method stub
		map.put(CoreType.result.toString(), JSON.toJSON(tree).toString());

	}

}
