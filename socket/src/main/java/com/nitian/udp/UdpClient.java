package com.nitian.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class UdpClient extends Thread {

	private DatagramSocket socket = null;

	private Integer port = 2048;// 默认端口

	private String ip = "127.0.0.1";// 默认ip地址

	private InetAddress inetAddress = null;// ip地址

	private List<String> list = new ArrayList<String>();

	public UdpClient() {
		// TODO Auto-generated constructor stub
		init(null, null);
	}

	public UdpClient(Integer port) {
		init(port, null);
	}

	public UdpClient(String ip) {
		init(null, ip);
	}

	public UdpClient(Integer port, String ip) {
		// TODO Auto-generated constructor stub
		init(port, ip);
	}

	public synchronized void push(String string) {
		list.add(string);
		System.out.println("list size:" + list.size());
		notify();
	}

	public void init(Integer port, String ip) {
		try {
			if (port != null) {
				this.port = port;
			}
			if (ip != null) {
				this.ip = ip;
			}
			socket = new DatagramSocket();
			inetAddress = InetAddress.getByName(this.ip);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				if (list.size() == 0) {
					System.out.println("list size" + list.size());
					wait();
				} else {
					byte[] sendBuffer = list.get(0).getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendBuffer,
							sendBuffer.length, this.inetAddress, this.port);
					socket.send(sendPacket);
					list.remove(0);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
