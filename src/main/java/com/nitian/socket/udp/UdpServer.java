package com.nitian.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class UdpServer {

	private Integer port = 8888;// 端口

	private DatagramSocket socket;// 服务器socket

	private List<String> list = new ArrayList<String>();// 消息队列

	private byte[] receiveBuffer = new byte[1024];// 字节缓冲区

	public UdpServer() throws IOException {
		// TODO Auto-generated constructor stub
		init(null);
	}

	public UdpServer(Integer port) throws IOException {
		init(port);
	}

	/**
	 * 初始化工作
	 * 
	 * @param port
	 * @throws IOException
	 */
	private void init(Integer port) throws IOException {
		if (port != null) {
			this.port = port;
		}
		socket = new DatagramSocket(this.port);
		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveBuffer,
					receiveBuffer.length);
			socket.receive(receivePacket);
			String result = new String(receivePacket.getData(), 0,
					receivePacket.getLength());
			System.out.println("length:" + receivePacket.getLength());
			System.out.println("result:" + result);
			System.out.println("port:" + receivePacket.getPort());
			System.out.println("ip:"
					+ receivePacket.getAddress().getHostAddress());
			list.add(result);
		}
	}

	public static void main(String[] args) throws IOException {
		new UdpServer();
	}
}
