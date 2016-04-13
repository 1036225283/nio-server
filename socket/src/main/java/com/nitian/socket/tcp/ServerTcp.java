package com.nitian.socket.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.nitian.socket.ApplicationContext;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

public class ServerTcp {

	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();

	private LogManager log = LogManager.getInstance();

	private Integer port;
	private ServerSocket serverSocket;

	public ServerTcp(int port) {
		this.port = port;
	}

	public ServerTcp() {
	}

	public void start() {
		try {
			if (port == null) {
				port = 8080;
			}
			Thread.currentThread().setName("线程：服务主线程");
			log.info(LogType.thread, this, Thread.currentThread().toString());
			serverSocket = new ServerSocket(port);
			log.info(LogType.debug, this, "server is start");
			while (true) {
				Socket socket = serverSocket.accept();
				log.dateInfo(LogType.time, this, "第一步：接收socket");
				applicationContext.getQueueParse().push(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

}
