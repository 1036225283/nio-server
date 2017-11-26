package com.nitian.socket.util.factory;

import com.nitian.socket.EngineSocketNIO;
import com.nitian.socket.util.pool.UtilPoolBuffer;
import com.nitian.socket.util.queue.UtilQueue;
import com.nitian.socket.util.store.CountStore;
import com.nitian.socket.util.store.CountStoreSelectionKey;
import com.nitian.socket.util.store.CountStoreSocket;
import com.nitian.socket.util.write.UtilHttpWriteSocket;
import com.nitian.socket.util.write.UtilWebSocketWriteSocket;
import com.nitian.socket.util.write.UtilWebSocketWriteSocketChannel;
import com.nitian.socket.util.write.UtilWrite;

/**
 * Created by 1036225283 on 2016/11/20.
 * 工厂函数，根据类名来返回生成的实例
 */
public class Factory {

	/**
	 * 获取计数存储socket等对象
	 *
	 * @param className
	 * @return
	 */
	public static CountStore getCountStore(String className) {
		if (className.equals(EngineSocketNIO.class.getName())) {
			return new CountStoreSocket();
		} else if (className.equals(EngineSocketNIO.class.getName())) {
			return new CountStoreSelectionKey();
		}
		return null;
	}

	/**
	 * 获取读线程队列
	 *
	 * @param className
	 * @param engineSocket
	 * @return
	 */
	public static UtilQueue getReadQueue(String className, EngineSocketNIO engineSocket) {
		if (className.equals(EngineSocketNIO.class.getName())) {
//			return new UtilQueueSocket(engineSocket);
		} else if (className.equals(EngineSocketNIO.class.getName())) {
//			return new UtilQueueSocketChannel(engineSocket);
		}
		return null;

	}

	/**
	 * 获取写线程队列
	 *
	 * @param className
	 * @param engineSocket
	 * @return
	 */
//	public static UtilQueue getWriteQueue(String className, EngineSocket engineSocket) {
//		System.out.println("------------------------------------" + className);
//		if (className.equals(EngineSocket.class.getName())) {
//			return new UtilQueueWrite(engineSocket);
//		} else if (className.equals(EngineSocketNIO.class.getName())) {
//			return new UtilQueueWrite(engineSocket);
//		}
//		return null;
//
//	}

	/**
	 * 获取buffer池
	 *
	 * @param className
	 * @return
	 */
	public static UtilPoolBuffer getPoolBuffer(String className, EngineSocketNIO engineSocket) {
		if (className.equals(EngineSocketNIO.class.getName())) {
			return null;
		} else if (className.equals(EngineSocketNIO.class.getName())) {
//			return new UtilPoolBuffer(engineSocket.getPoolMax(), engineSocket.getPoolTotal(), null);
		}
		return null;

	}

	/**
	 * 获取 http write
	 *
	 * @param className
	 * @return
	 */
	public static UtilWrite getUtilHttpWrite(String className) {
		System.out.println("------------------------------------" + className);
		if (className.equals(EngineSocketNIO.class.getName())) {
			return new UtilHttpWriteSocket();
		} else if (className.equals(EngineSocketNIO.class.getName())) {
			return new UtilHttpWriteSocket();
		}
		return null;
	}

	/**
	 * 获取 web socket write
	 *
	 * @param className
	 * @return
	 */
	public static UtilWrite getUtilWebSocketWrite(String className) {
		if (className.equals(EngineSocketNIO.class.getName())) {
			return new UtilWebSocketWriteSocket();
		} else if (className.equals(EngineSocketNIO.class.getName())) {
			return new UtilWebSocketWriteSocketChannel();
		}
		return null;
	}
}
