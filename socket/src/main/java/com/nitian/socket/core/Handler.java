package com.nitian.socket.core;

public abstract class Handler extends Thread {

	private HandlerContext handlerContext;

	public Object get(String key) {
		return handlerContext.get(key);
	}

	public abstract void handle();

	/**
	 * 注入HandlerContext
	 * 
	 * @param handlerContext
	 */
	public void setHandlerContext(HandlerContext handlerContext) {
		this.handlerContext = handlerContext;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		handle();
	}

}
