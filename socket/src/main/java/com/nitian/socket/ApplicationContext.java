package com.nitian.socket;

import com.nitian.socket.util.UtilHandler;
import com.nitian.socket.util.UtilPoolByte;
import com.nitian.socket.util.UtilPoolHandlerContext;
import com.nitian.socket.util.UtilPoolMap;
import com.nitian.socket.util.UtilPoolThread;
import com.nitian.socket.util.UtilQueueRead;

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
	private UtilQueueRead queueRead;

	public ApplicationContext() {
		// TODO Auto-generated constructor stub
		// 线程池不需要追踪
		poolSocketThread = new UtilPoolThread(10);
		poolHandlerThread = new UtilPoolThread(10);

		// 对象池需要追踪
		poolByte = new UtilPoolByte(poolMax, poolTotal, null);// socket读取缓冲区(lend:replay)
		poolMap = new UtilPoolMap(poolMax, poolTotal);// 解析数据缓冲区(lend:)
		poolHandlerContext = new UtilPoolHandlerContext(poolMax, poolTotal);// 业务处理器上下文缓冲区(lend:)

		// {url:handler}业务处理器，不需要追踪
		utilHandler = new UtilHandler();

		// 读，写消息队列
		queueRead = new UtilQueueRead();

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

	public UtilQueueRead getQueueRead() {
		return queueRead;
	}

}
