package com.nitian.socket.bio;

import com._1036225283.util.self.log.LogManager;
import com._1036225283.util.self.log.LogType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerTcp {

	public static void main(String[] args) {
		new ServerTcp().start();

	}

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
				new Read(socket).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

}
