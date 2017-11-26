package com.nitian.socket.util.protocol.ssl;

import com.nitian.socket.core.CoreProtocol;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.protocol.read.ProtocolReadHandler;
import com.nitian.util.log.LogManager;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * HTTPS协议解析器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolHttpsReadHandler extends ProtocolReadHandler {

    protected static LogManager log = LogManager.getInstance();

    @Override
    public boolean handle(Map<String, Object> map, ByteBuffer buffer, byte[] bs) {
        try {

            buffer.flip();
            int length = buffer.remaining();
            buffer.get(bs, 0, length);
            //先判断是什么协议
            int nHandshakeType = SSL.getHandshakeProtocol(bs);
            if (nHandshakeType == SSL.SSLHandshake) {
                SSLHandshakeHandler.hander(bs);
            } else if (nHandshakeType == SSL.SSHApplicationData) {

            }

            map.put(CoreType.protocol.toString(), CoreProtocol.HTTPS.toString());
            return true;
        } catch (Exception e) {
            log.error(e, "解析HTTP协议出错了!!!");
            return false;
        }
    }
}
