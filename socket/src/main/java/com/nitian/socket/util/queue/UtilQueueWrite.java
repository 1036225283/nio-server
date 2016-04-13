package com.nitian.socket.util.queue;

import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.util.thread.ThreadWrite;
import com.nitian.util.log.LogType;

/**
 * 读消息队列：处理map数据
 * 
 * @author 1036225283
 *
 */
public class UtilQueueWrite extends UtilQueue<Map<String, String>> {

	private ApplicationContext applicationContext;

	public UtilQueueWrite(ApplicationContext applicationContext) {
		// TODO Auto-generated constructor stub
		this.applicationContext = applicationContext;
	}

	@Override
	public void handle(Map<String, String> t) {
		// TODO Auto-generated method stub
		log.dateInfo(LogType.time, this, "第四步：开始发送消息");
		applicationContext.getPoolSocketThread().execute(new ThreadWrite(t));
		log.info(LogType.thread, this, Thread.currentThread().toString());
		log.dateInfo(LogType.time, this, "第四步：结束发送消息");
	}

}
