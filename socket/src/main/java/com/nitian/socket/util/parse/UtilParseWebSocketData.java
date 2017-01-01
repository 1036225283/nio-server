package com.nitian.socket.util.parse;

import java.util.HashMap;
import java.util.Map;

import com.nitian.socket.util.websocket.UtilWebSocket;
import com.nitian.util.java.UtilByte;
import com.nitian.util.log.LogManager;

/**
 * 接口定义
 *
 * @author 1036225283
 */
public class UtilParseWebSocketData {

    protected static LogManager log = LogManager.getInstance();

    /**
     * 返回一个map，type=text/bin,data=string/byte[]
     */
    private Map<String, Object> map = new HashMap<String, Object>();

    private byte[] params = null;// 外部传入参数
    private int data_length = 0;// 数据长度
    private byte[] mark_data = null;// 掩码数据
    private int start = 0;// 真正数据开始的地方
    private int data_type = 0;// 1标识文本,2标识字节流
    private int op_code = 0;// 操作码

    /**
     * 设置数据长度
     */
    public void set_data_length() {
        byte b = params[1];
        b = (byte) (b & 127);// 0111 1111
        if (b < 126) {
            data_length = b;
            return;
        } else if (b == 126) {
            int value = 0;
            value = value | 0xff & params[2];
            value = value << 8;
            value = value | 0xff & params[3];
            data_length = value;
            return;
        } else if (b == 127) {
            log.info("websocket_frame", "数据长度较大");
        }
    }

    /**
     * 设置数据类型
     */
    public void set_data_type() {
        if (op_code == 2) {
            data_type = 2;
        } else if (op_code == 3) {
            data_type = 3;
        }
    }


    public UtilParseWebSocketData(byte[] bs) {
        // TODO Auto-generated constructor stub
        this.params = bs;
    }

    /**
     * 解析接收到的WebSocket数据
     *
     * @param bs
     * @param length
     */
    public static String parse(byte[] bs, int length) {
        print(bs, length);
        return "";
    }


    /**
     * 获取数据的长度
     *
     * @param bs
     * @return
     * @0-125之间，即当前数据的长度
     * @126，后面的两个自己就是数据的长度
     * @127后面的4个字节就是数据的长度
     */

    public static void print(byte[] bs, int size) {
        log.info("websocket_frame", UtilByte.toBin(bs));
        log.info("websocket_frame", UtilByte.toHex(bs));
        log.info("websocket_frame", UtilByte.toDec(bs));
        log.info("websocket_frame", new String(bs, 0, size));
    }

}
