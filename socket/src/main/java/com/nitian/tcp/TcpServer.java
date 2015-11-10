package com.nitian.tcp;

import java.io.IOException;
import java.net.ServerSocket;

public class TcpServer {

	private int port;
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
			port = 1234;
			serverSocket = new ServerSocket(port);
			while (true) {
				TcpClient tcpSocket = new TcpClient();
				tcpSocket.setSocket(serverSocket.accept());
				tcpSocket.start();
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
