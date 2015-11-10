package com.nitian.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TcpSocketClient extends Thread {
	private int port;
	private Socket socket;
	private InetAddress ip;
	private String name;

	public TcpSocketClient(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		try {
			ip = InetAddress.getByName("127.0.0.1");
			port = 1234;
			socket = new Socket(ip, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				sleep(10000);
				bufferedWriter.write("客户端"+name);
				bufferedWriter.newLine();
				bufferedWriter.flush();
				String result = bufferedReader.readLine();
				System.out.println("客户端的值" + result);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
