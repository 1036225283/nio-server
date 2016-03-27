package com.nitian.socket.util;

import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.Handler;
import com.nitian.socket.core.Message;

/**
 * 读消息队列：处理map数据
 * 
 * @author 1036225283
 *
 */
public class UtilQueueWrite extends UtilQueue<Map<String, String>> {

	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();

	public UtilQueueWrite() {
		// TODO Auto-generated constructor stub
		start();
	}

	@Override
	public void handle(Map<String, String> t) {
		// TODO Auto-generated method stub
		System.out.println("------action:queueRead->handle");
		// 构造handler环境
		Message handlerContext = new Message(t);
		Handler handler = applicationContext.getUtilHandler().get(t.get("url"));
		// 如果没有url对应的handler，就使用默认的handler
		if (handler == null) {
			handler = applicationContext.getUtilHandler().get("default");
		}
		applicationContext.getPoolHandlerThread().execute(null);
	}

}
