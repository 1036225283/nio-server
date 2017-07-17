package com.nitian.handler.tree;

import com.alibaba.fastjson.JSON;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.util.column.tree.rbt.RBTree;

import java.util.Map;

public class RBTClearHandler extends Handler {

    @Override
    public void handle(Map<String, String> map) {
        // TODO Auto-generated method stub

        TreeFactory.setRbTree(new RBTree<>());
        map.put(CoreType.result.toString(), JSON.toJSONString(TreeFactory.getRbTree()));
    }

}