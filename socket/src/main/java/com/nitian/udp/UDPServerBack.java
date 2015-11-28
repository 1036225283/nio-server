package com.nitian.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerBack {

	public static void main(String[] args) throws IOException {
		DatagramSocket server = new DatagramSocket(5050);
		byte[] recvBuf = new byte[1024];

		DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
		server.receive(recvPacket);
		String recvStr = new String(recvPacket.getData(), 0,
				recvPacket.getLength());
		System.out.println("Hello World!" + recvStr);

		int port = recvPacket.getPort();
		InetAddress addr = recvPacket.getAddress();
		String sendStr = "Hello ! I'm Server";
		byte[] sendBuf;
		sendBuf = sendStr.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length,
				addr, port);
		server.send(sendPacket);
		server.close();
	}
}
