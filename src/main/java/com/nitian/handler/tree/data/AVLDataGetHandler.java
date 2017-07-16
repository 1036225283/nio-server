package com.nitian.handler.tree.data;

import com.nitian.handler.UtilResult;
import com.nitian.handler.tree.UtilAVL;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;
import com.nitian.util.column.tree.avl.AVLTree;
import com.nitian.util.column.tree.avl.Node;

import java.util.Map;

/**
 * get(tree)
 */
public class AVLDataGetHandler extends Handler {

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
        AVLTree<String, String> avl = UtilAVL.getAVL(sessionId);

        long startTime = System.nanoTime();
        Node<String, String> node = avl.get(key);
        long endTime = System.nanoTime();

        String value = "value not find";
        if (node != null) {
            value = node.getValue() + "";
        }
        map.put(CoreType.result.toString(), UtilResult.success(key, value, endTime - startTime));

    }

}
