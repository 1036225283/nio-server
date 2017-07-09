package com.nitian.handler.tree;

import com.nitian.util.column.tree.avl.AVLTree;
import com.nitian.util.column.tree.rbt.RBTree;

/**
 * 获取tree的单例
 * Created by 1036225283 on 2017/4/19.
 */
public class TreeFactory {

    private static AVLTree<Integer, Integer> avlTree = new AVLTree<>();
    private static RBTree<Integer, Integer> rbTree = new RBTree<>();

    public static AVLTree<Integer, Integer> getInstance() {
        return avlTree;
    }


    public static AVLTree<Integer, Integer> getAvlTree() {
        return avlTree;
    }

    public static void setAvlTree(AVLTree<Integer, Integer> tree) {
        avlTree = tree;
    }

    public static RBTree<Integer, Integer> getRbTree() {
        return rbTree;
    }

    public static void setRbTree(RBTree<Integer, Integer> tree) {
        rbTree = tree;
    }
}
