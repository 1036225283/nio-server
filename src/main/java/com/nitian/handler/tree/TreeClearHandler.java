package com.nitian.handler.tree;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.util.column.tree.Tree;

public class TreeClearHandler extends Handler {

	@Override
	public void handle(Map<String, String> map) {
		// TODO Auto-generated method stub

		TreeFactory.setSelfAvl(new Tree<>());
		map.put(CoreType.result.toString(), JSON.toJSON(TreeFactory.getSelfAvl()).toString());
	}

}
