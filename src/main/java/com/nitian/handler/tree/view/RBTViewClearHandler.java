package com.nitian.handler.tree.view;

import com.alibaba.fastjson.JSON;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.core.Session;
import com.nitian.util.column.tree.rbt.RBTree;

import java.util.Map;

public class RBTViewClearHandler extends Handler {

    @Override
    public void handle(Map<String, String> map) {
        // TODO Auto-generated method stub

        String sessionId = map.get(CoreType.sessionId.toString());
        Map<String, Object> session = Session.get(sessionId);
        RBTree<String, String> rbt = new RBTree<>();
        session.put("rbt", rbt);
        map.put(CoreType.result.toString(), JSON.toJSON(rbt).toString());
    }

}
