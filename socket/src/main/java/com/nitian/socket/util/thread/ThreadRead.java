package com.nitian.socket.util.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseHttpRead;

/**
 * 线程读数据
 * 
 * @author 1036225283
 *
 */
public class ThreadRead extends Thread {

	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();

	private Socket socket;

	public ThreadRead(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			byte[] bs = applicationContext.getPoolByte().lend();

			long applicationId = applicationContext.getApplicationSocket().put(
					socket);
			int size = socket.getInputStream().read(bs);

			Map<String, String> map = applicationContext.getPoolMap().lend();
			map.put(CoreType.applicationId.toString(),
					String.valueOf(applicationId));
			map.put(CoreType.size.toString(), String.valueOf(size));

			UtilParseHttpRead httpRead = new UtilParseHttpRead(new String(bs,
					0, size), map);
			applicationContext.getPoolByte().repay(bs);// 偿还bytes给对象池
			applicationContext.getQueueRead().push(httpRead.getMap());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
