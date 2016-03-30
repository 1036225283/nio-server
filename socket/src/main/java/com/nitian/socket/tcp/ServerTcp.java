package com.nitian.socket.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.util.thread.ThreadRead;

public class ServerTcp {

	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();

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
			serverSocket = new ServerSocket(port);
			System.out.println("server is start ... ...");
			while (true) {
				Socket socket = serverSocket.accept();
				applicationContext.getPoolSocketThread().execute(
						new ThreadRead(socket));
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
