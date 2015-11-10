package com.nitian.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import com.nitian.tcp.TcpRead;
import com.nitian.tcp.TcpWrite;

public class TcpSocketClient {
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
			TcpRead read = new TcpRead(socket);
			read.start();
			TcpWrite tcpWrite = new TcpWrite(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public void

}
