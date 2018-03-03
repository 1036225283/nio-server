package com.nitian.handler.tree.view;

import com._1036225283.util.self.column.tree.rbt.RBTree;
import com.alibaba.fastjson.JSON;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.UtilSession;

import java.util.Map;

public class RBTViewClearHandler extends Handler {

    @Override
    public void handle(Map<String, Object> map) {
        // TODO Auto-generated method stub

        String sessionId = map.get(CoreType.sessionId.toString()).toString();
        Map<String, Object> session = UtilSession.get(sessionId);
        RBTree<Integer, Integer> rbt = new RBTree<>();
        session.put("rbt", rbt);
        map.put(CoreType.result.toString(), JSON.toJSON(rbt).toString());
    }

}
