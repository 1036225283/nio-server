package com.nitian.handler.tree.view;

import com.alibaba.fastjson.JSON;
import com.nitian.handler.tree.UtilAVL;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.util.column.tree.avl.AVLTree;

import java.util.Map;

public class AVLViewClearHandler extends Handler {

    @Override
    public void handle(Map<String, String> map) {
        // TODO Auto-generated method stub

        String sessionId = map.get(CoreType.sessionId.toString());
        AVLTree<String, String> avl = UtilAVL.getAVL(sessionId);
        avl = new AVLTree<>();
        map.put(CoreType.result.toString(), JSON.toJSON(avl).toString());
    }

}