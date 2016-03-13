package com.nitian.socket.util;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class UtilApplication {

	/**
	 * <applicationId,socket>队列
	 */
	private static Map<Integer, Socket> applicationMap = new HashMap<Integer, Socket>();
	private static int currendId = 0;

	/**
	 * 创建aplicationId
	 * 
	 * @return
	 */
	public synchronized static Integer createApplicationId() {
		currendId = currendId + 1;
		return currendId;
	}

	public static void put(Socket socket) {
		int id = createApplicationId();
		applicationMap.put(id, socket);
	}

	public static void remove(int id) {
		applicationMap.remove(id);
	}
}
