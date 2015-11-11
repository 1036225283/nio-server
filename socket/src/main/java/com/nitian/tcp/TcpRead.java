package com.nitian.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.nitian.util.java.UtilByte;

public class TcpRead extends Thread {

	private Socket socket;

	private SocketUser socketUser;

	private byte[] bs = new byte[1024 * 8 + 4];

	public TcpRead(SocketUser socketUser) {
		this.setSocketUser(socketUser);
	}

	public TcpRead(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			InputStream inputStream = socket.getInputStream();
			while (true) {
				byte[] length = new byte[4];
				inputStream.read(length, 0, 4);
				int size = UtilByte.bytesToInt(length);
				inputStream.read(bs, 0, size);
				System.out.println("字符串：" + new String(bs, 0, size));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public SocketUser getSocketUser() {
		return socketUser;
	}

	public void setSocketUser(SocketUser socketUser) {
		this.socketUser = socketUser;
	}
}
