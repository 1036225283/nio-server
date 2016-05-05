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
			log.info("websocket_frame", "这是文本帧");
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
		log.info("websocket_frame", "payLoadLength=" + payLoadLength);

		return "";
	}

	public static byte getOpCode(byte b) {
		byte low = 15;
		return (byte) (b & low);
	}

	public static long getPayloadLength(byte[] bs) {
		byte b = bs[1];
		b = (byte) (b & 127);// 0111 1111
		if (b < 126) {
			log.info("websocket_frame", "数据长度：" + b);
		} else if (b == 126) {
			System.out.println(UtilByte.toBin(bs[2]));
			System.out.println(UtilByte.toBin(bs[3]));
			log.info("websocket_frame", "数据长度中等");
			if (bs[2] != 0) {
				return bs[2] * 256 + bs[3];
			} else {
				return (long) bs[3];
			}
		} else if (b == 127) {
			log.info("websocket_frame", "数据长度较大");
		}
		return b;
	}

	public static void print(byte[] bs, int size) {
		log.info("websocket_frame", UtilByte.toBin(bs));
		log.info("websocket_frame", UtilByte.toHex(bs));
		log.info("websocket_frame", UtilByte.toDec(bs));
		log.info("websocket_frame", new String(bs, 0, size));
	}

}
