package com.nitian.handler.graph.view;

import com.alibaba.fastjson.JSON;
import com.nitian.handler.UtilResult;
import com.nitian.handler.tree.UtilTREE;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;
import com.nitian.util.column.tree.avl.AVLTree;

import java.util.Map;

public class GraphViewInsertHandler extends Handler {

    @Override
    public void handle(Map<String, String> map) {
        // TODO Auto-generated method stub
        String param = map.get(CoreType.param.toString());
        Map<String, String> paramMap = UtilParam.getParam(param);

        String key = paramMap.get("key");
        if (key == null) {
            map.put(CoreType.result.toString(), UtilResult.keyIsNull("key is null"));
            return;
        }

        String sessionId = map.get(CoreType.sessionId.toString());
        AVLTree<Integer, Integer> avl = UtilTREE.getAVL(sessionId);

        avl.put(Integer.valueOf(key), Integer.valueOf(key));
        avl.eachLeft();
        map.put(CoreType.result.toString(), JSON.toJSON(avl).toString());
    }

}
