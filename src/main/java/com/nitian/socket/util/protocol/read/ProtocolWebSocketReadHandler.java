package com.nitian.socket.util.protocol.read;

import com.nitian.socket.core.CoreProtocol;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseRead;
import com.nitian.socket.util.websocket.UtilWebSocket;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * Web socket Upgrade协议解析器
 * Created by 1036225283 on 2016/12/17.
 */
public class ProtocolWebSocketReadHandler extends ProtocolReadHandler {

    protected static LogManager log = LogManager.getInstance();

    @Override
    public boolean handle(Map<String, Object> map, ByteBuffer buffer, byte[] bs) {
        try {
//            int RSV = 1 << 2;
//            int OPCODE = 1 << 3;
//            int MASK = (1 << 4);
//            int PAYLOAD_LENGTH = 0;
//            int PAYLOAD_DATA = 0;
//            int MASKING_kEY = 0;
//            new BigInteger();

            buffer.flip();
            int length = buffer.remaining();
            buffer.get(bs, 0, length);
            try {
                byte FIN = UtilWebSocket.getFIN(bs);
                if (FIN == 0) {// 暂不处理连续帧
                    log.info(LogType.debug, "----web socket 这是连续帧");
                } else {
                    log.info(LogType.debug, "----web socket 这是唯一帧");
                }

                // 获取掩码
                byte MASK = UtilWebSocket.getMASK(bs);
                if (MASK == 1) {
                    log.info(LogType.debug, "----web socket 这是掩码标志");
                } else {
                    log.info(LogType.debug, "----web socket 没有掩码标志");
                }

                byte OPCODE = UtilWebSocket.getOPCODE(bs);
                if (OPCODE == 0) {
                    log.info(LogType.debug, "----web socket OPCODE = 0");
                } else if (OPCODE == 1) {// 文本帧
                    map.put(CoreType.param_type.toString(), CoreType.text.toString());
                    int size = UtilWebSocket.getPAYLOADLENGTH(bs);
                    int offset = 0;
                    if (size < 126) {
                        offset = 2;
                    } else if (size >= 126 && size < Integer.MAX_VALUE) {
                        offset = 4;
                    } else if (length > Integer.MAX_VALUE) {
                        offset = 6;
                    }
                    // 如果有掩码
                    if (MASK == 1) {
                        offset = offset + 4;
                        for (int i = offset; i < size; i++) {
//					bs[i] = bs[i]^MASK
                        }
                    } else {
                        String textValue = new String(bs, offset + 4, size);
                    }
                    String textValue = new String(bs, offset + 4, size);
                    log.info(LogType.debug, "----web socket textValue" + textValue);
                    log.info(LogType.debug, "----web socket OPCODE = 1");
                    map.put(CoreType.stop.toString(), CoreType.stop.toString());
                    return true;
                } else if (OPCODE == 2) {// 字节帧
                    map.put(CoreType.stop.toString(), CoreType.stop.toString());
                    log.info(LogType.debug, "----web socket OPCODE = 2");
                    return true;
                } else if (OPCODE == 8) {// 关闭帧
                    map.put(CoreType.stop.toString(), CoreType.stop.toString());
                    log.info(LogType.debug, "----web socket OPCODE = 8");
                    return true;
                } else if (OPCODE == 9) {// PING帧
                    map.put(CoreType.stop.toString(), CoreType.stop.toString());
                    log.info(LogType.debug, "----web socket OPCODE = 9");
                    return true;
                } else if (OPCODE == 10) {// PONG帧
                    map.put(CoreType.stop.toString(), CoreType.stop.toString());
                    log.info(LogType.debug, "----web socket OPCODE = 10");
                    return true;
                }

            } catch (Exception e) {
                return false;
            }

            String request = new String(bs, 0, length);
            log.info(LogType.debug, "----WEB SOCKET 读取数据 = " + request);
            String[] strings = request.split("\r\n");
            String ip = UtilParseRead.getIp(strings);
            String port = UtilParseRead.getPort(strings);
            String secWebSocketKey = UtilParseRead.getSecWebSocketKey(strings);
            String secWebSocketAccept = UtilWebSocket.getSecWebSocketAccept(secWebSocketKey);
            map.put(CoreType.ip.toString(), ip);
            map.put(CoreType.port.toString(), port);
            map.put(CoreType.sec_websocket_accept.toString(), secWebSocketAccept);
            map.put(CoreType.protocol.toString(), CoreProtocol.WEBSOCKETUPGRADE.toString());
            map.put(CoreType.size.toString(), String.valueOf(request.length()));
            return true;
        } catch (Exception e) {
            log.error(e, "解析HTTP协议出错了!!!");
            return false;
        }
    }
}
