package com.nitian.socket.core;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ApplicationSocket {

	/**
	 * <applicationId,socket>队列
	 */
	private Map<Long, Socket> applicationMap = new HashMap<Long, Socket>();
	private long currendId = 0;

	/**
	 * 创建aplicationId
	 * 
	 * @return
	 */
	public synchronized long createApplicationId() {
		currendId = currendId + 1;
		return currendId;
	}

	public long put(Socket socket) {
		long id = createApplicationId();
		applicationMap.put(id, socket);
		return id;
	}

	public Socket remove(long id) {
		return applicationMap.remove(id);
	}
}
