package com.nitian.server;

import java.io.IOException;
import java.net.ServerSocket;

public class TcpServerSocket {

	private int port;
	private ServerSocket serverSocket;

	TcpServerSocket() {

		try {
			port = 1234;
			serverSocket = new ServerSocket(port);
			while (true) {
				TcpSocket tcpSocket = new TcpSocket();
				tcpSocket.setSocket(serverSocket.accept());
				tcpSocket.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	public static void main(String[] args) {
		new TcpServerSocket();

	}

}
