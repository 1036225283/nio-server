package com.nitian.socket.util.write;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseWebSocketWrite;

public class UtilWebSocketWrite {

	public static void write(Map<String, String> map,
			ApplicationContext applicationContext) {
		long applicationId = Long.valueOf(map.get(CoreType.applicationId
				.toString()));
		Socket socket = applicationContext.getApplicationSocket().remove(
				applicationId);
		UtilParseWebSocketWrite webSocketWrite = new UtilParseWebSocketWrite(
				map);
		byte[] bs = webSocketWrite.getResult();
		try {
			socket.getOutputStream().write(bs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			applicationContext.getPoolMap().repay(map);
		}
	}
}
