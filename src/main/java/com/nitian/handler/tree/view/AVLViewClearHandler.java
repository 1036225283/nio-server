package com.nitian.handler.tree.view;

import com.alibaba.fastjson.JSON;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.UtilSession;
import com.nitian.util.column.tree.avl.AVLTree;

import java.util.Map;

public class AVLViewClearHandler extends Handler {

    @Override
    public void handle(Map<String, Object> map) {
        // TODO Auto-generated method stub

        String sessionId = map.get(CoreType.sessionId.toString()).toString();
        Map<String, Object> session = UtilSession.get(sessionId);
        AVLTree<Integer, Integer> avl = new AVLTree<>();
        session.put("avl", avl);
        map.put(CoreType.result.toString(), JSON.toJSON(avl).toString());
    }

}

