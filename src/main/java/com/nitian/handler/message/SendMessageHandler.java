package com.nitian.handler.message;

import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.util.parse.UtilParam;

import java.util.Map;

/**
 * 发送消息接口
 * param:strToId,strFromId,strMessage,strAction
 * strToId:接受消息的人,对应一个socket
 * strFromId:发送消息的人
 * strMessage:发送的消息
 * strAction:业务字段
 */
public class SendMessageHandler extends Handler {

    private static ColumnSelectionKey selectionKey = ColumnSelectionKey.getInstance();

    @Override
    public void handle(Map<String, String> map) {
        // TODO Auto-generated method stub
        //解析参数

        String param = map.get(CoreType.param.toString());
        Map<String, String> paramMap = UtilParam.getParam(param);
        String strToId = paramMap.get("strToId");
        if (strToId == null) {
            map.put(CoreType.result.toString(), "strToId not exist");
            return;
        }

        String strFromId = paramMap.get("strFromId");
        if (strFromId == null) {
            map.put(CoreType.result.toString(), "strFromId not exist");
            return;
        }


        String strMessage = paramMap.get("strMessage");
        if (strMessage == null || strMessage.equals("")) {
            map.put(CoreType.result.toString(), "strMessage not exist or not is ''");
            return;
        }


    }

}
