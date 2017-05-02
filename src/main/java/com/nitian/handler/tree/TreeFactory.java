package com.nitian.handler.tree;

import com.nitian.util.column.tree.Tree;
import com.nitian.util.column.tree.demo1.AVLTree;
import com.nitian.util.column.tree.rbtree.RBTree;

/**
 * 获取tree的单例
 * Created by 1036225283 on 2017/4/19.
 */
public class TreeFactory {

    private static Tree<Integer, Integer> tree;
    private static AVLTree<Integer> avl = new AVLTree<>();
    private static Tree<Integer, Integer> selfAvl = new Tree<>();
    private static RBTree<Integer, Integer> rbTree = new RBTree<>();

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
        return avl;
    }

    public static void setAvl(AVLTree<Integer> avlTree) {
        avl = avlTree;

    }

    public static Tree<Integer, Integer> getSelfAvl() {
        return selfAvl;
    }

    public static void setSelfAvl(Tree<Integer, Integer> tree) {
        selfAvl = tree;
    }

    public static RBTree<Integer, Integer> getRbTree() {
        return rbTree;
    }

    public static void setRbTree(RBTree<Integer, Integer> tree) {
        rbTree = tree;
    }
}
