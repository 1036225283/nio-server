package com.nitian.socket.core;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

public class ApplicationSocket {

	/**
	 * <applicationId,socket>队列
	 */
	private Map<Long, Socket> applicationMap = new HashMap<Long, Socket>();
	private long currendId = 0;
	protected LogManager log = LogManager.getInstance();

	/**
	 * 创建aplicationId
	 * 
	 * @return
	 */
	public synchronized long createApplicationId() {
		currendId = currendId + 1;
		return currendId;
	}

	public synchronized long put(Socket socket) {
		long id = createApplicationId();
		applicationMap.put(id, socket);
		return id;
	}

	public synchronized Socket remove(long id) {
		log.info(LogType.socket, this, "map 中socket：" + applicationMap);
		return applicationMap.remove(id);
	}
}
