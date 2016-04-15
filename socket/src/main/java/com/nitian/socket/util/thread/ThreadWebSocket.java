package com.nitian.socket.util.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.CoreType;
import com.nitian.util.java.UtilByte;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

/**
 * WebSocket守护线程
 * 
 * @author 1036225283
 *
 */
public class ThreadWebSocket implements Runnable {

	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();

	protected LogManager log = LogManager.getInstance();

	private Socket socket;
	private boolean stop = true;

	public ThreadWebSocket(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub

		while (stop) {
			try {
				log.info(LogType.thread, this, "线程：读socket："
						+ Thread.currentThread().toString());
				byte[] bs = applicationContext.getPoolByte().lend();
				int size = socket.getInputStream().read(bs);
				if (size != -1) {
					byte[] bs2 = new byte[size];
					UtilByte byte1 = new UtilByte();
					byte1.setValue(bs2);
					byte1.copy(bs);
					System.out.println(byte1.toBin());
					System.out.println(byte1.toHex());
					System.out.println(byte1.toDec());
					System.out.println("接受：" + new String(bs, 0, size));

					long applicationId = applicationContext
							.getApplicationSocket().put(socket);
					log.info(LogType.debug, this, "size=" + size);
					Map<String, String> map = applicationContext.getPoolMap()
							.lend();
					map.put(CoreType.applicationId.toString(),
							String.valueOf(applicationId));
					map.put(CoreType.protocol.toString(), "WEBSOCKET");
					map.put("result", "this is web socket");
					map.put(CoreType.size.toString(), String.valueOf(size));
					applicationContext.getPoolByte().repay(bs);// 偿还bytes给对象池
					applicationContext.getQueueRead().push(map);
				} else {
					applicationContext.getPoolByte().repay(bs);
					applicationContext.getListWebSocketThread().remove(this);
					stop = false;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
