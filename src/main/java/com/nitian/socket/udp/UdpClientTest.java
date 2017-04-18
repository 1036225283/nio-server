package com.nitian.socket.udp;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class UdpClientTest {

	public UdpClientTest(String value) throws SocketException,
			UnknownHostException {
		UdpClient client = new UdpClient(8888, "127.0.0.1");
		client.start();
		timer(client, value);
	}

	public void timer(final UdpClient client, final String value) {
		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			int i = 0;

			public void run() {
				for (int j = 0; j < 10000; j++) {
					client.push("i=" + i + value);
					i++;
				}
			}
		}, 1000, 50);
	}

	public static void main(String[] args) throws SocketException,
			UnknownHostException {
		new UdpClientTest("我是客户端1");
	}
}
