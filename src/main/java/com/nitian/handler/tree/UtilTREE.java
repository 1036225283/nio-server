package com.nitian.handler.tree;

import com._1036225283.util.self.column.tree.avl.AVLTree;
import com._1036225283.util.self.column.tree.rbt.RBTree;
import com.nitian.socket.util.UtilSession;

import java.util.Map;

/**
 * avl tool
 * Created by xws on 7/9/17.
 */
public class UtilTREE {

    public static AVLTree<Integer, Integer> getAVL(String sessionId) {
        Map<String, Object> session = UtilSession.get(sessionId);
        AVLTree<Integer, Integer> avl = (AVLTree<Integer, Integer>) session.get("avl");
        if (avl == null) {
            avl = new AVLTree<>();
            session.put("avl", avl);
        }
        return avl;
    }


    public static RBTree<Integer, Integer> getRBT(String sessionId) {
        Map<String, Object> session = UtilSession.get(sessionId);
        RBTree<Integer, Integer> rbt = (RBTree<Integer, Integer>) session.get("rbt");
        if (rbt == null) {
            rbt = new RBTree<>();
            session.put("rbt", rbt);
        }
        return rbt;
    }

}
