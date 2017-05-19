package com.nitian.handler.tree;

import com.alibaba.fastjson.JSON;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.util.column.tree.demo1.AVLTree;

import java.util.Map;

/**
 * get(tree)AVL
 */
public class AVLGetHandler extends Handler {

	private static AVLTree<Integer> avl = TreeFactory.getAVL();

	@Override
	public void handle(Map<String, String> map) {
		// TODO Auto-generated method stub
		map.put(CoreType.result.toString(), JSON.toJSON(avl).toString());

	}

}
