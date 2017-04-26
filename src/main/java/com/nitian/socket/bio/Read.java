package com.nitian.socket.bio;

import java.io.IOException;
import java.net.Socket;

public class Read extends Thread {

	private Socket socket;
	private int index = 0;

	public Read(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				index = index + 1;
				byte[] bs = new byte[1024 * 64];
				int size = socket.getInputStream().read(bs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
