package com.nitian.handler.tree.view;

import com.nitian.handler.UtilResult;
import com.nitian.handler.tree.UtilTREE;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;
import com.nitian.util.column.tree.avl.AVLTree;

import java.util.Map;

public class AVLViewSetHandler extends Handler {

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
        String value = paramMap.get("value");
        if (value == null) {
            map.put(CoreType.result.toString(), UtilResult.keyIsNull("value is null"));
            return;
        }


        String sessionId = map.get(CoreType.sessionId.toString());
        AVLTree<String, String> avl = UtilTREE.getAVL(sessionId);

        long startTime = System.nanoTime();
        avl.put(key,value);
        long endTime = System.nanoTime();
        map.put(CoreType.result.toString(), UtilResult.success(key, value, endTime - startTime));
    }

}
