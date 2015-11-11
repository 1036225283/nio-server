package com.nitian.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

	private Integer port;
	private ServerSocket serverSocket;

	TcpServer(int port) {
		this.port = port;
		init();
	}

	TcpServer() {
		init();
	}

	public void init() {
		try {
			if (port == null) {
				port = 1234;
			}
			serverSocket = new ServerSocket(port);
			while (true) {
				Socket socket = serverSocket.accept();
				SocketUser socketUser = new SocketUser();
				socketUser.setSocket(socket);
				socketUser.setTcpRead(new TcpRead());
				socketUser.setTcpWrite(new TcpWrite());
				SocketConstant.list.add(socketUser);
				System.out.println("server is start");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void main(String[] args) {
		new TcpServer();
	}

}
