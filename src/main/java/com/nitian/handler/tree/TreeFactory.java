package com.nitian.handler.tree;

import com.nitian.util.column.tree.Tree;
import com.nitian.util.column.tree.demo1.AVLTree;

/**
 * 获取tree的单例
 * Created by 1036225283 on 2017/4/19.
 */
public class TreeFactory {

	private static Tree<Integer, Integer> tree;
	private static AVLTree<Integer> avl;
	private static Tree<Integer, Integer> selfAvl = new Tree<>();

	public static Tree<Integer, Integer> getInstance() {
		if (tree == null) {
			// tree = Test.createTree();
			// tree = new Tree<>();
			// for (int i = 0; i < 10; i++) {
			// tree.put(i, i);
			// }
		}

		return tree;
	}

	public static AVLTree<Integer> getAVL() {
		// if (avl == null) {
		// avl = new AVLTree<>();
		// for (int i = 0; i < 100; i++) {
		// avl.insert(i);
		// }
		// }
		return avl;
	}

	public static Tree<Integer, Integer> getSelfAvl() {
		return selfAvl;
	}

	public static void setSelfAvl(Tree<Integer, Integer> tree) {
		selfAvl = tree;
	}
}
