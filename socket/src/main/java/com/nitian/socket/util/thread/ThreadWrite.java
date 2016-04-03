package com.nitian.socket.util.thread;

import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.write.UtilHttpWrite;
import com.nitian.socket.util.write.UtilWebSocketWrite;

/**
 * 线程写数据
 * 
 * @author 1036225283
 *
 */
public class ThreadWrite extends Thread {

	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();

	private Map<String, String> map;

	public ThreadWrite(Map<String, String> map) {
		// TODO Auto-generated constructor stub
		this.map = map;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String protocol = map.get(CoreType.protocol.toString());
		if (protocol.equals("HTTP")) {
			UtilHttpWrite.write(map, applicationContext);
		} else if (protocol.equals("WEBSOCKET")) {
			UtilWebSocketWrite.write(map, applicationContext);
		}
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}
