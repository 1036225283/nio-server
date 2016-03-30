package com.nitian.socket.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TcpRead extends Thread {

	private Socket socket;

	private byte[] bs = new byte[1024 * 8 + 4];

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			InputStream inputStream = socket.getInputStream();
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

}
