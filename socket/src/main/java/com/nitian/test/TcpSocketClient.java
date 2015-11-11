package com.nitian.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import com.nitian.tcp.TcpWrite;

public class TcpSocketClient {
	private int port;
	private Socket socket;
	private InetAddress ip;

	public TcpSocketClient() {
		// TODO Auto-generated constructor stub
		try {
			ip = InetAddress.getByName("127.0.0.1");
			port = 1234;
			socket = new Socket(ip, port);
			// TcpRead read = new TcpRead(socket);
			// read.start();
			TcpWrite tcpWrite = new TcpWrite();
			tcpWrite.setSocket(socket);
			tcpWrite.start();
			timer(tcpWrite);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void timer(final TcpWrite tcpWrite) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				String write = "abcdefg123";
				System.out.println(write.length());
				tcpWrite.push(write);
			}
		}, 10000, 5000);
	}

	public static void main(String[] args) {
		TcpSocketClient client = new TcpSocketClient();
	}
}
