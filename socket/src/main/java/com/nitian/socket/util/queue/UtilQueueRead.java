package com.nitian.socket.util.queue;

import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.Handler;
import com.nitian.socket.core.HandlerContext;

public class UtilQueueRead extends UtilQueue<Map<String, String>> {

	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();

	public UtilQueueRead() {
		// TODO Auto-generated constructor stub
		start();
	}

	@Override
	public void handle(Map<String, String> t) {
		// TODO Auto-generated method stub
		System.out.println("------action:queueRead->handle");
//		HandlerContext handlerContext = applicationContext
//				.getPoolHandlerContext().lend();
//		Handler handler = applicationContext.getUtilHandler().get(t.get("url"));
//		handler.setHandlerContext(handlerContext);
//		applicationContext.getPoolHandlerThread().execute(handler);
	}

}
