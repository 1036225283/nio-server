package com.nitian.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TcpSocket extends Thread {

	private Socket socket;

	public TcpSocket() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream()));
			while (true) {
				String result = bufferedReader.readLine();
				System.out.println("客户端的值" + result);
				bufferedWriter.write("我的回馈");
				bufferedWriter.newLine();
				bufferedWriter.flush();
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
}
