package com.nitian.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.nitian.util.java.UtilByte;
import com.nitian.util.string.UtilStringHex;

public class TcpRead extends Thread {

	private Socket socket;

	private SocketUser socketUser;

	public static int index = 0;

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
				System.out.println("index:" + index++);
				byte[] length = new byte[4];
				inputStream.read(length, 0, 4);
				int size = UtilByte.bytesToInt(length);
				byte[] bs = new byte[size + 4];
				inputStream.read(bs, 4, size);
				System.out.println("二进制：" + UtilStringHex.bytesHexStr(bs));
				System.out.println("字符串：" + new String(bs));
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
