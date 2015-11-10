package com.nitian.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class TcpServer {

	private Integer port;
	private ServerSocket serverSocket;

	public static Map<String, SocketUser> map = new HashMap<String, SocketUser>();

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
				TcpRead tcpRead = new TcpRead(socket);
				tcpRead.start();
				TcpWrite tcpWrite = new TcpWrite(socket);
//				tcpWrite.push("ddd");
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
