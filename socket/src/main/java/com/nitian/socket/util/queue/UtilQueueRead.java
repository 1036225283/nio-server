package com.nitian.socket.util.queue;

import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
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

		String url = t.get(CoreType.url.toString());

		Class<Handler> handlerClass = applicationContext.getHandlerFactory()
				.get(url);
		if (handlerClass != null) {
			Handler handler;
			try {
				handler = handlerClass.newInstance();
				handler.setApplicationContext(applicationContext);
				handler.setMap(t);
				applicationContext.getPoolHandlerThread().execute(handler);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		log.info(LogType.thread, this, Thread.currentThread().toString());
	}

}
