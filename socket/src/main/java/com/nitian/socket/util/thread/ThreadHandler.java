package com.nitian.socket.util.thread;

import com.nitian.socket.core.Handler;

public class ThreadHandler extends Thread {

	private Handler handler;

	public ThreadHandler(Handler handler) {
		// TODO Auto-generated constructor stub
		this.handler = handler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
}
