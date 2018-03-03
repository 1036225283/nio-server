package com.nitian.socket.util.protocol.write;

import com._1036225283.util.self.log.LogManager;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParam;

import java.util.Map;

/**
 * HTTP-Write协议解析器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolXwsWriteHandler extends ProtocolWriteHandler {

    protected static LogManager log = LogManager.getInstance();

    @Override
    public byte[] handle(Map<String, Object> map) {
        String result = map.get(CoreType.result.toString()).toString();
        StringBuffer sb = new StringBuffer();
        sb.append("XWS:XWS/1.1");
        sb.append("\r\n");
        sb.append("Url:" + map.get(CoreType.url.toString()));
        sb.append("\r\n");
        sb.append("Server:XWS-Coyote/1.1");
        sb.append("\r\n");
        sb.append("Accept-Charset: utf-8");
        sb.append("\r\n");
        sb.append("Length:").append(result.length());
        sb.append("\r\n");
        sb.append("Content:");
        sb.append(UtilParam.encode(result));
        map.put(CoreType.close.toString(), "false");
        return sb.toString().getBytes();
    }
}
