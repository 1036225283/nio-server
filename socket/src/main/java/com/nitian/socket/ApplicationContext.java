package com.nitian.socket;

import com.nitian.socket.core.ApplicationSocket;
import com.nitian.socket.core.DefaultHandler;
import com.nitian.socket.util.HandlerFactory;
import com.nitian.socket.util.UtilPoolThread;
import com.nitian.socket.util.list.UtilListWebSocketThread;
import com.nitian.socket.util.pool.UtilPoolByte;
import com.nitian.socket.util.pool.UtilPoolMap;
import com.nitian.socket.util.queue.UtilQueueParse;
import com.nitian.socket.util.queue.UtilQueueRead;
import com.nitian.socket.util.queue.UtilQueueWrite;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

public class ApplicationContext {

	private static ApplicationContext context = new ApplicationContext();
	private int poolMax = 800;
	private int poolTotal = 200;

	private UtilPoolByte poolByte;
	private UtilPoolThread poolSocketThread;
	private UtilPoolThread poolHandlerThread;
	private UtilPoolThread poolWebSocketThread;
	private UtilPoolMap poolMap;
	private HandlerFactory handlerFactory;
	// 消息队列
	private UtilQueueRead queueRead;
	private UtilQueueWrite queueWrite;
	private UtilQueueParse queueParse;
	private ApplicationSocket applicationSocket;
	private UtilListWebSocketThread listWebSocketThread;

	private LogManager log = LogManager.getInstance();

	public ApplicationContext() {
		// TODO Auto-generated constructor stub

		// log.putType(LogType.time.toString());
		log.putType("websocket_frame");
		// 线程池不需要追踪
		poolSocketThread = new UtilPoolThread(200);
		poolHandlerThread = new UtilPoolThread(200);
		poolWebSocketThread = new UtilPoolThread(10);

		// 对象池需要追踪
		poolByte = new UtilPoolByte(poolMax, poolTotal, null);// socket读取缓冲区(lend:replay)
		poolMap = new UtilPoolMap(poolMax, poolTotal);// 解析数据缓冲区(lend:)

		// {url:handler}业务处理器，不需要追踪
		handlerFactory = new HandlerFactory();

		// 读，写消息队列
		queueRead = new UtilQueueRead(this);
		queueWrite = new UtilQueueWrite(this);
		queueParse = new UtilQueueParse(this);
		new Thread(queueRead, "线程：读队列线程").start();
		new Thread(queueWrite, "线程：写队列线程").start();
		new Thread(queueParse, "线程：解析列线程").start();

		// 默认handler
		handlerFactory.regist("default", DefaultHandler.class);

		// 全局socket
		applicationSocket = new ApplicationSocket();
		listWebSocketThread = new UtilListWebSocketThread();

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

	public int getPoolMax() {
		return poolMax;
	}

	public void setPoolMax(int poolMax) {
		this.poolMax = poolMax;
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

	public ApplicationSocket getApplicationSocket() {
		return applicationSocket;
	}

	public void setApplicationSocket(ApplicationSocket applicationSocket) {
		this.applicationSocket = applicationSocket;
	}

	public UtilQueueWrite getQueueWrite() {
		return queueWrite;
	}

	public HandlerFactory getHandlerFactory() {
		return handlerFactory;
	}

	public void setHandlerFactory(HandlerFactory handlerFactory) {
		this.handlerFactory = handlerFactory;
	}

	public UtilListWebSocketThread getListWebSocketThread() {
		return listWebSocketThread;
	}

	public UtilPoolThread getPoolWebSocketThread() {
		return poolWebSocketThread;
	}

	public UtilQueueParse getQueueParse() {
		return queueParse;
	}

	public void setQueueParse(UtilQueueParse queueParse) {
		this.queueParse = queueParse;
	}

}
