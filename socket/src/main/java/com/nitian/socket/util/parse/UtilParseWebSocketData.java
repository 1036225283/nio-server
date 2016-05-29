package com.nitian.socket.util.parse;

import com.nitian.util.java.UtilByte;
import com.nitian.util.log.LogManager;

public class UtilParseWebSocketData {

	protected static LogManager log = LogManager.getInstance();

	/**
	 * 解析接收到的WebSocket数据
	 * 
	 * @param bs
	 * @param length
	 */
	public static String parse(byte[] bs, int length) {
		print(bs, length);
		byte fin = UtilByte.getBit(bs[0], 8);
		if (fin == 0) {// 暂不处理连续帧
			log.info("websocket_frame", "这是连续帧fin");
			return "";
		} else {
			log.info("websocket_frame", "这是唯一帧fin");
		}

		byte opcode = getOpCode(bs[0]);
		if (opcode == 0) {
			log.info("websocket_frame", "这是连续帧");
			return "";// 暂不处理连续帧
		} else if (opcode == 1) {// 文本帧
			long size = getPayloadLength(bs);
			int offset = 0;
			if (size < 126) {
				offset = 2;
			} else if (size == 126) {
				offset = 4;
			} else if (length == 127) {
				offset = 6;
			}
			String textValue = new String(bs, offset, length);
			log.info("websocket_frame", "这是文本帧");
			log.info("websocket", textValue);
			log.info("websocket", "------------------------------------------");
		} else if (opcode == 2) {// 字节帧
			log.info("websocket_frame", "这是字节帧");
		} else if (opcode == 8) {// 关闭帧
			log.info("websocket_frame", "这是关闭帧");
		} else if (opcode == 9) {// PING帧
			log.info("websocket_frame", "这是ping帧");
		} else if (opcode == 10) {// PONG帧
			log.info("websocket_frame", "这是pong帧");
		}

		byte mask = UtilByte.getBit(bs[1], 8);
		if (mask == 1) {
			log.info("websocket_frame", "这是掩码");
		} else {
			log.info("websocket_frame", "没有掩码");
		}
		long payLoadLength = getPayloadLength(bs);
		System.out.println();
		log.info("websocket_frame", "payLoadLength=" + payLoadLength);

		return "";
	}

	/**
	 * 获取一个字节的00001111中1111数据
	 * 
	 * @param b
	 * @return
	 */
	public static byte getOpCode(byte b) {
		byte low = 15;
		return (byte) (b & low);
	}

	/**
	 * 获取数据的长度
	 * 
	 * @0-125之间，即当前数据的长度
	 * @126，后面的两个自己就是数据的长度
	 * @127后面的4个字节就是数据的长度
	 * 
	 * @param bs
	 * @return
	 */
	public static long getPayloadLength(byte[] bs) {
		byte b = bs[1];
		b = (byte) (b & 127);// 0111 1111
		if (b < 126) {
			log.info("websocket_frame", "数据长度：" + b);
			return b;
		} else if (b == 126) {
			int value = 0;
			value = value | 0xff & bs[2];
			value = value << 8;
			value = value | 0xff & bs[3];
			return value;
		} else if (b == 127) {
			log.info("websocket_frame", "数据长度较大");
		}
		return b;
	}

	// public static

	public static void print(byte[] bs, int size) {
		log.info("websocket_frame", UtilByte.toBin(bs));
		log.info("websocket_frame", UtilByte.toHex(bs));
		log.info("websocket_frame", UtilByte.toDec(bs));
		log.info("websocket_frame", new String(bs, 0, size));
	}

}
