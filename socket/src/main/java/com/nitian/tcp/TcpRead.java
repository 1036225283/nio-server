package com.nitian.tcp;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

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

			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					socket.getInputStream());
			while (true) {
				System.out.println("index:" + index++);
				int size = bufferedInputStream.available();
				if (size != 0) {
					byte[] bs = new byte[size];
					bufferedInputStream.read(bs);
					System.out.println("二进制：" + UtilStringHex.bytesHexStr(bs));
					System.out.println("字符串：" + new String(bs));
				}
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
