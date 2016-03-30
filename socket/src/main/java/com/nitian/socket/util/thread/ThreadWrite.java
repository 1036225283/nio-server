package com.nitian.socket.util.thread;

import java.net.Socket;
import java.util.Map;

import com.nitian.socket.ApplicationContext;

/**
 * 线程写数据
 * 
 * @author 1036225283
 *
 */
public class ThreadWrite extends Thread {

	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();
	
	private Map<String, Object> map;

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		Socket socket
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
