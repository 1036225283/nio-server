package com.nitian.socket.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.nitian.socket.ApplicationContext;

public class ServerTcp {

	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();
	private Integer port;
	private ServerSocket serverSocket;

	ServerTcp(int port) {
		this.port = port;
		init();
	}

	ServerTcp() {
		init();
	}

	public void init() {
		try {
			if (port == null) {
				port = 8080;
			}
			serverSocket = new ServerSocket(port);
			System.out.println("server is start ... ...");
			while (true) {
				Socket socket = serverSocket.accept();
				applicationContext.getPoolThread().execute(
						new ThreadRead(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void main(String[] args) {
		new ServerTcp(88);
	}

}
