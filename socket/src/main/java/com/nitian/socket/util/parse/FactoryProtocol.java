package com.nitian.socket.util.parse;

import com.nitian.socket.core.CoreProtocol;
import com.nitian.socket.core.CoreType;
import com.nitian.util.log.LogManager;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

public class FactoryProtocol {

    protected static LogManager log = LogManager.getInstance();


    public static void parse(String protocol, Map<String, String> map, ByteBuffer buffer, byte[] bs)
            throws UnsupportedEncodingException {
        // TODO Auto-generated constructor stub

        if (protocol.equals(CoreProtocol.HTTP.toString())) {
            map.put(CoreType.protocol.toString(), CoreProtocol.HTTP.toString());
            UtilParseHttpRead.parse(map, buffer, bs);
        } else if (protocol.equals(CoreProtocol.WEBSOCKET.toString())) {
            map.put(CoreType.protocol.toString(), CoreProtocol.WEBSOCKET.toString());
            UtilParseWebSocketRead.parse(map, buffer, bs);
        }

//        String[] strings = request.split("\r\n");

//        log.info(LogType.debug, "----原生数据=" + request);
//        log.info(LogType.debug, "----headers=" + strings.length);

//        String protocol = UtilParseRead.getProtocol(strings);


    }

}
