package com.nitian.socket;

import com.nitian.socket.util.UtilHandler;
import com.nitian.socket.util.UtilPoolByte;
import com.nitian.socket.util.UtilPoolHandlerContext;
import com.nitian.socket.util.UtilPoolMap;
import com.nitian.socket.util.UtilPoolThread;

public class ApplicationContext {

	private static ApplicationContext context = new ApplicationContext();
	private int poolMax = 800;
	private int poolTotal = 2;

	private UtilPoolByte poolByte;
	private UtilPoolThread poolSocketThread;
	private UtilPoolThread poolHandlerThread;
	private UtilPoolMap poolMap;
	private UtilPoolHandlerContext poolHandlerContext;
	private UtilHandler utilHandler;

	public ApplicationContext() {
		// TODO Auto-generated constructor stub
		poolByte = new UtilPoolByte(poolMax, poolTotal, null);
		poolSocketThread = new UtilPoolThread(10);
		poolHandlerThread = new UtilPoolThread(10);
		poolMap = new UtilPoolMap(poolMax, poolTotal);
		poolHandlerContext = new UtilPoolHandlerContext(poolMax, poolTotal);
		utilHandler = new UtilHandler();
	}

	public static ApplicationContext getInstance() {
		return context;
	}

	public UtilPoolByte getPoolByte() {
		return poolByte;
	}

	public UtilPoolMap getPoolMap() {
		return poolMap;
	}

	public UtilPoolHandlerContext getPoolHandlerContext() {
		return poolHandlerContext;
	}

	public int getPoolMax() {
		return poolMax;
	}

	public void setPoolMax(int poolMax) {
		this.poolMax = poolMax;
	}

	public UtilHandler getUtilHandler() {
		return utilHandler;
	}

	public UtilPoolThread getPoolSocketThread() {
		return poolSocketThread;
	}

	public UtilPoolThread getPoolHandlerThread() {
		return poolHandlerThread;
	}

}
