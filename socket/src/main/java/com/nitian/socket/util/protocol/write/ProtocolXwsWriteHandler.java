package com.nitian.socket.util.protocol.write;

import com.nitian.socket.core.CoreType;
import com.nitian.util.log.LogManager;

import java.util.Map;

/**
 * HTTP-Write协议解析器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolXwsWriteHandler extends ProtocolWriteHandler {

    protected static LogManager log = LogManager.getInstance();

    @Override
    public byte[] handle(Map<String, String> map) {
        String result = map.get(CoreType.result.toString());
        StringBuffer sb = new StringBuffer();
        sb.append("XWS:XWS/1.1");
        sb.append("\r\n");
        sb.append("Url:/love");
        sb.append("\r\n");
        sb.append("Server:XWS-Coyote/1.1");
        sb.append("\r\n");
        sb.append("Accept-Charset: utf-8");
        sb.append("\r\n");
        sb.append("Content-Length:").append(result.length());
        sb.append("\r\n");
        sb.append("Content:");
        sb.append(result);
        map.put(CoreType.close.toString(), "false");
        return sb.toString().getBytes();
    }
}
