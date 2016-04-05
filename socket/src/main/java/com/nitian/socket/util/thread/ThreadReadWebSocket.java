package com.nitian.socket.util.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.CoreType;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

/**
 * 读取webSocket数据
 * 
 * @author 1036225283
 *
 */
public class ThreadReadWebSocket implements Runnable {

	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();

	protected LogManager log = LogManager.getInstance();

	private Socket socket;

	public ThreadReadWebSocket(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {
			try {
				log.info(LogType.thread, this, "线程：读socket："
						+ Thread.currentThread().toString());
				byte[] bs = applicationContext.getPoolByte().lend();

				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println("isClosed:" + socket.isClosed());
				System.out.println("isBound:" + socket.isBound());
				System.out.println("isConnected:" + socket.isConnected());
				System.out.println("isInputShutdown:"
						+ socket.isInputShutdown());
				System.out.println("isOutputShutdown:"
						+ socket.isOutputShutdown());
				long applicationId = applicationContext.getApplicationSocket()
						.put(socket);

				int size = socket.getInputStream().read(bs);
				log.info(LogType.debug, this, "size=" + size);

				Map<String, String> map = applicationContext.getPoolMap()
						.lend();
				map.put(CoreType.applicationId.toString(),
						String.valueOf(applicationId));
				map.put(CoreType.protocol.toString(), "WEBSOCKET");
				map.put(CoreType.size.toString(), String.valueOf(size));
				System.out.println("接受：" + new String(bs, 0, size));
				// new UtilParseProtocol(new String(bs, 0, size), map);
				applicationContext.getPoolByte().repay(bs);// 偿还bytes给对象池
				applicationContext.getQueueRead().push(map);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
