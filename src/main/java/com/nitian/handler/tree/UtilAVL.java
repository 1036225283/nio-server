package com.nitian.handler.tree;

import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Session;
import com.nitian.util.column.tree.avl.AVLTree;

import java.util.Map;

/**
 * avl tool
 * Created by xws on 7/9/17.
 */
public class UtilAVL {

    public static AVLTree getAVL(String sessionId) {
        Map<String, Object> session = Session.get(sessionId);
        AVLTree<String, String> avl = (AVLTree<String, String>) session.get("avl");
        if (avl == null) {
            avl = new AVLTree<>();
            session.put("avl", avl);
        }
        return avl;
    }
}
