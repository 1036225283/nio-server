package com.nitian.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.nitian.util.UtilApplication;

public class ServerTcp {

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
		new ServerTcp(8080);
	}

}
