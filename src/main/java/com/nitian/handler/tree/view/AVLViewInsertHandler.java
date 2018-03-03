package com.nitian.handler.tree.view;

import com._1036225283.util.self.column.tree.avl.AVLTree;
import com.alibaba.fastjson.JSON;
import com.nitian.handler.UtilResult;
import com.nitian.handler.tree.UtilTREE;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;

import java.util.Map;

public class AVLViewInsertHandler extends Handler {

    @Override
    public void handle(Map<String, Object> map) {
        // TODO Auto-generated method stub
        String param = map.get(CoreType.param.toString()).toString();
        Map<String, String> paramMap = UtilParam.getParam(param);

        String key = paramMap.get("key");
        if (key == null) {
            map.put(CoreType.result.toString(), UtilResult.keyIsNull("key is null"));
            return;
        }

        String sessionId = map.get(CoreType.sessionId.toString()).toString();
        AVLTree<Integer, Integer> avl = UtilTREE.getAVL(sessionId);

        avl.put(Integer.valueOf(key), Integer.valueOf(key));
        avl.eachLeft();
        map.put(CoreType.result.toString(), JSON.toJSON(avl).toString());
    }

}
