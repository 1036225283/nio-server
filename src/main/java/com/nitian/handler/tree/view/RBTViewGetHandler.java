package com.nitian.handler.tree.view;

import com.nitian.handler.UtilResult;
import com.nitian.handler.tree.TreeFactory;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;
import com.nitian.util.column.tree.rbt.Node;
import com.nitian.util.column.tree.rbt.RBTree;

import java.util.Map;

/**
 * get(tree)
 */
public class RBTViewGetHandler extends Handler {

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

        RBTree<Integer, Integer> tree = TreeFactory.getRbTree();
        long startTime = System.nanoTime();
        Node<Integer, Integer> node = tree.get(Integer.valueOf(key));
        long endTime = System.nanoTime();

        String value = "value not find";
        if (node != null) {
            value = node.getValue() + "";
        }
        map.put(CoreType.result.toString(), UtilResult.success(key, value, endTime - startTime));

    }

}
