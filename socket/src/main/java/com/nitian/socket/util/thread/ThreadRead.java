package com.nitian.socket.util.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseProtocol;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

/**
 * 线程读数据
 * 
 * @author 1036225283
 *
 */
public class ThreadRead implements Runnable {

	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();

	protected LogManager log = LogManager.getInstance();

	private Socket socket;

	public ThreadRead(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			log.info(LogType.thread, this, "线程：读socket："
					+ Thread.currentThread().toString());
			byte[] bs = applicationContext.getPoolByte().lend();

			long applicationId = applicationContext.getApplicationSocket().put(
					socket);

			int size = socket.getInputStream().read(bs);
			log.info(LogType.debug, this, "size=" + size);

			Map<String, String> map = applicationContext.getPoolMap().lend();
			map.put(CoreType.applicationId.toString(),
					String.valueOf(applicationId));
			map.put(CoreType.size.toString(), String.valueOf(size));

			new UtilParseProtocol(new String(bs, 0, size), map);
			applicationContext.getPoolByte().repay(bs);// 偿还bytes给对象池
			String protocol = map.get(CoreType.protocol.toString());
			if (protocol.equals("WEBSOCKET")) {
				ThreadReadWebSocket webSocket = new ThreadReadWebSocket(socket);
				boolean result = applicationContext.getListWebSocketThread()
						.put(webSocket);
				if (result == false) {
					map.put(CoreType.sec_websocket_accept.toString(),
							"i am sorry");
				} else {
					applicationContext.getPoolWebSocketThread().execute(
							webSocket);
				}
			}
			applicationContext.getQueueRead().push(map);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
