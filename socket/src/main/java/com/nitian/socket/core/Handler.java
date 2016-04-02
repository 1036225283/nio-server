package com.nitian.socket.core;

import java.util.Map;

import com.nitian.socket.ApplicationContext;

public abstract class Handler implements Runnable {

	public abstract void handle(Map<String, String> map);

	private ApplicationContext applicationContext;
	private Map<String, String> map;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		handle(map);
		afterHandle();
	}

	private void afterHandle() {
		applicationContext.getQueueWrite().push(map);
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
