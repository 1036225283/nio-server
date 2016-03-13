package com.nitian.socket;

import com.nitian.socket.util.UtilPoolByte;
import com.nitian.socket.util.UtilPoolThread;

public class ApplicationContext {

	private static ApplicationContext context = new ApplicationContext();
	private UtilPoolByte poolByte;
	private UtilPoolThread poolThread;

	public ApplicationContext() {
		// TODO Auto-generated constructor stub
		poolByte = new UtilPoolByte(800, 2, null);
		poolThread = new UtilPoolThread(10);
	}

	public static ApplicationContext getInstance() {
		return context;
	}

	public UtilPoolByte getPoolByte() {
		return poolByte;
	}

	public void setPoolByte(UtilPoolByte poolByte) {
		this.poolByte = poolByte;
	}

	public UtilPoolThread getPoolThread() {
		return poolThread;
	}

	public void setPoolThread(UtilPoolThread poolThread) {
		this.poolThread = poolThread;
	}

}
