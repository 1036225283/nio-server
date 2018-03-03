package com.nitian.handler.graph.view;

import com._1036225283.util.self.column.tree.avl.AVLTree;
import com.alibaba.fastjson.JSON;
import com.nitian.handler.tree.UtilTREE;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;

import java.util.Map;

public class GraphViewSelectHandler extends Handler {

    @Override
    public void handle(Map<String, Object> map) {
        // TODO Auto-generated method stub
        String param = map.get(CoreType.param.toString()).toString();


        String sessionId = map.get(CoreType.sessionId.toString()).toString();
        AVLTree<Integer, Integer> avl = UtilTREE.getAVL(sessionId);

        avl.eachLeft();
        map.put(CoreType.result.toString(), JSON.toJSON(avl).toString());
    }

}
