package com.nitian.socket.util.websocket;


import com._1036225283.util.self.encrypt.UtilBase64;
import com._1036225283.util.self.encrypt.UtilMd5;
import com._1036225283.util.self.java.UtilByte;

public class UtilWebSocket {

	public static String getSecWebSocketAccept(String SecWebSocketKey) {
		SecWebSocketKey = SecWebSocketKey + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
		byte[] bs = UtilMd5.stringToSHA1_(SecWebSocketKey);
		String value = UtilBase64.encode(bs);
		return value;
	}

	/**
	 * 获取 web socket 协议的 OPCODE
	 * 获取一个字节的00001111中1111数据
	 *
	 * @param bytes
	 * @return
	 */
	public static byte getOPCODE(byte[] bytes) {
		byte b = bytes[0];
		byte low = 15;
		return (byte) (b & low);
	}

	public static byte getFIN(byte[] bytes) {
		return UtilByte.getBit(bytes[0], 8);
	}

	/**
	 * 获取掩码
	 *
	 * @param bytes
	 * @return
	 */
	public static byte getMASK(byte[] bytes) {
		return UtilByte.getBit(bytes[1], 8);
	}

	/**
	 * 0-125之间，即当前数据的长度
	 * 126，后面的两个自己就是数据的长度
	 * 127后面的4个字节就是数据的长度
	 *
	 * @param bs
	 * @return
	 */
	public static int getPAYLOADLENGTH(byte[] bs) {
		byte b = bs[1];
		b = (byte) (b & 127);// 0111 1111
		if (b < 126) {
			return b;
		} else if (b == 126) {
			int value = 0;
			value = value | 0xff & bs[2];
			value = value << 8;
			value = value | 0xff & bs[3];
			return value;
		} else if (b == 127) {
		}
		return b;
	}

	/**
	 * 获取文本帧的内容
	 *
	 * @param bytes
	 * @return
	 */
	public static String getTEXT(byte[] bytes) {
		return null;
	}

	/**
	 * 获取二进制数据
	 *
	 * @param bytes
	 * @return
	 */
	public static byte[] getBIN(byte[] bytes) {
		return null;
	}
}
