package com.nitian.socket.udp;

import com._1036225283.util.self.random.UtilRandom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class UdpWrite extends Thread {

	private DatagramSocket socket = null;

	private Integer port = 2048;// 默认端口

	private String ip = "127.0.0.1";// 默认ip地址

	public void init(Integer port, String ip) {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		DatagramSocket client = new DatagramSocket();
		String sendStr = UtilRandom.createHexString(512);
		byte[] sendBuf;
		sendBuf = sendStr.getBytes();
		InetAddress addr = InetAddress.getByName("127.0.0.1");
		int port = 5050;
		DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length,
				addr, port);
		client.send(sendPacket);
		// client.
		byte[] recvBuf = new byte[100];
		DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
		client.receive(recvPacket);
		String recvStr = new String(recvPacket.getData(), 0,
				recvPacket.getLength());
		System.out.println("收到:" + recvStr);
		client.close();
	}
}
