package com.nitian.handler.tree.view;

import com.alibaba.fastjson.JSON;
import com.nitian.handler.UtilResult;
import com.nitian.handler.tree.UtilTREE;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;
import com.nitian.util.column.tree.rbt.RBTree;

import java.util.Map;

public class RBTViewInsertHandler extends Handler {

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
        RBTree<String, String> rbt = UtilTREE.getRBT(sessionId);

        rbt.put(key, key);
        rbt.eachLeft();
        map.put(CoreType.result.toString(), JSON.toJSONString(rbt));
    }

}
