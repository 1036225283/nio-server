package com.nitian.socket.util.queue;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseProtocol;
import com.nitian.socket.util.thread.ThreadReadWebSocket;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

/**
 * 解析HTTP/WEBSOCKET请求队列
 * 
 * @author 1036225283
 *
 */
public class UtilQueueParse extends UtilQueue<Socket> {

	private ApplicationContext applicationContext;

	protected LogManager log = LogManager.getInstance();

	public UtilQueueParse(ApplicationContext applicationContext) {
		// TODO Auto-generated constructor stub
		this.applicationContext = applicationContext;
	}

	@Override
	public void handle(Socket socket) {
		// TODO Auto-generated method stub

		try {
			log.dateInfo(LogType.time, this, "第二步：开始解析http或者websocket数据");
			log.info(LogType.thread, this, "线程：读socket："
					+ Thread.currentThread().toString());
			byte[] bs = applicationContext.getPoolByte().lend();
			int size = socket.getInputStream().read(bs);
			if (size == -1) {
				socket.close();
				applicationContext.getPoolByte().repay(bs);
				return;
			}
			log.info(LogType.debug, this, "size=" + size);

			long applicationId = applicationContext.getApplicationSocket().put(
					socket);

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
			log.dateInfo(LogType.time, this, "第二步：结束解析http或者websocket数据");
			applicationContext.getQueueRead().push(map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
