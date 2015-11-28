package com.nitian.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class UdpServer {

	private Integer port = 2048;// 端口

	private DatagramSocket socket;// 服务器socket

	private List<String> list = new ArrayList<String>();// 消息队列

	private byte[] receiveBuffer = new byte[1024];// 字节缓冲区

	public UdpServer() {
		// TODO Auto-generated constructor stub
		init(null);
	}

	public UdpServer(Integer port) {
		init(port);
	}

	/**
	 * 初始化工作
	 * 
	 * @param port
	 */
	private void init(Integer port) {
		if (port != null) {
			this.port = port;
		}
		try {
			socket = new DatagramSocket(this.port);
			while (true) {
				DatagramPacket receivePacket = new DatagramPacket(
						receiveBuffer, receiveBuffer.length);
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
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		new UdpServer();
	}
}
