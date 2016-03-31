package com.nitian.socket.util.queue;

import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.util.log.LogType;

public class UtilQueueRead extends UtilQueue<Map<String, String>> {

	private ApplicationContext applicationContext;

	public UtilQueueRead(ApplicationContext applicationContext) {
		// TODO Auto-generated constructor stub
		this.applicationContext = applicationContext;
	}

	@Override
	public void handle(Map<String, String> t) {
		// TODO Auto-generated method stub

		applicationContext.getQueueWrite().push(t);
		// HandlerContext handlerContext = applicationContext
		// .getPoolHandlerContext().lend();
		// Handler handler =
		// applicationContext.getUtilHandler().get(t.get("url"));
		// handler.setHandlerContext(handlerContext);
		// applicationContext.getPoolHandlerThread().execute(handler);
		log.info(LogType.thread, this, Thread.currentThread().toString());
	}

}
