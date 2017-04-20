package com.nitian.handler.tree;

import com.nitian.util.column.tree.Tree;
import sun.security.jca.GetInstance;

/**
 * 获取tree的单例
 * Created by 1036225283 on 2017/4/19.
 */
public class TreeFactory {

	private static Tree<Integer, Integer> tree;

	public static Tree<Integer, Integer> getInstance() {
		if (tree == null) {
			tree = new Tree<>();
			for (int i = 0; i < 10; i++) {
				tree.put(i, i);
			}
		}
		return tree;
	}
}
