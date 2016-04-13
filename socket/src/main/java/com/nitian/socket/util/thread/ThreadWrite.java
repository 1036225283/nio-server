package com.nitian.socket.util.thread;

import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.write.UtilHttpWrite;
import com.nitian.socket.util.write.UtilWebSocketWrite;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

/**
 * 线程写数据
 * 
 * @author 1036225283
 *
 */
public class ThreadWrite extends Thread {

	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();

	protected LogManager log = LogManager.getInstance();

	private Map<String, String> map;

	public ThreadWrite(Map<String, String> map) {
		// TODO Auto-generated constructor stub
		this.map = map;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		log.dateInfo(LogType.time, this, "第五步：开始包装发送消息");
		String protocol = map.get(CoreType.protocol.toString());
		if (protocol.equals("HTTP")) {
			UtilHttpWrite.write(map, applicationContext);
		} else if (protocol.equals("WEBSOCKET")) {
			UtilWebSocketWrite.write(map, applicationContext);
		}
		log.dateInfo(LogType.time, this, "第五步：结束包装发送消息");
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}
