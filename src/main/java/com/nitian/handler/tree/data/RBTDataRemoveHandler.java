package com.nitian.handler.tree.data;

import com.nitian.handler.UtilResult;
import com.nitian.handler.tree.UtilTREE;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;
import com.nitian.util.column.tree.rbt.RBTree;

import java.util.Map;

public class RBTDataRemoveHandler extends Handler {

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
        RBTree<Integer, Integer> rbt = UtilTREE.getRBT(sessionId);
        long startTime = System.nanoTime();
        rbt.remove(Integer.valueOf(key));
        long endTime = System.nanoTime();
        map.put(CoreType.result.toString(), UtilResult.success(key, "OK", endTime - startTime));

    }

}
