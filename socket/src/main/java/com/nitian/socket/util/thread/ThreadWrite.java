package com.nitian.socket.util.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseHttpWrite;

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
		long applicationId = Long.valueOf(map.get(CoreType.applicationId
				.toString()));
		Socket socket = applicationContext.getApplicationSocket().remove(
				applicationId);
		UtilParseHttpWrite httpWrite = new UtilParseHttpWrite(map);
		byte[] bs = httpWrite.getResult();
		try {
			socket.getOutputStream().write(bs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			applicationContext.getPoolMap().repay(map);
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}
