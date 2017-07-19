package com.nitian.handler.tree;

import com.nitian.socket.core.Session;
import com.nitian.util.column.tree.avl.AVLTree;
import com.nitian.util.column.tree.rbt.RBTree;

import java.util.Map;

/**
 * avl tool
 * Created by xws on 7/9/17.
 */
public class UtilTREE {

    public static AVLTree<String, String> getAVL(String sessionId) {
        Map<String, Object> session = Session.get(sessionId);
        AVLTree<String, String> avl = (AVLTree<String, String>) session.get("avl");
        if (avl == null) {
            avl = new AVLTree<>();
            session.put("avl", avl);
        }
        return avl;
    }


    public static RBTree<String, String> getRBT(String sessionId) {
        Map<String, Object> session = Session.get(sessionId);
        RBTree<String, String> rbt = (RBTree<String, String>) session.get("rbt");
        if (rbt == null) {
            rbt = new RBTree<>();
            session.put("rbt", rbt);
        }
        return rbt;
    }
}
