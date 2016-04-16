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

		return "";
	}

	public static byte getOpCode(byte b) {
		byte low = 15;
		return (byte) (b & low);
	}

	public static void print(byte[] bs, int size) {
		byte[] bs2 = new byte[size];
		UtilByte byte1 = new UtilByte();
		byte1.setValue(bs2);
		byte1.copy(bs);
		log.info("websocket_frame", byte1.toBin());
		log.info("websocket_frame", byte1.toHex());
		log.info("websocket_frame", byte1.toDec());
		log.info("websocket_frame", new String(bs, 0, size));
	}

}
