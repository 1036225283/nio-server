package com.nitian.udp;

import java.util.Timer;
import java.util.TimerTask;

public class UdpClientTest {

	public UdpClientTest(String value) {
		UdpClient client = new UdpClient("182.254.131.51");
		client.start();
		timer(client, value);
	}

	public void timer(final UdpClient client, final String value) {
		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			int i = 0;

			public void run() {
				client.push("i=" + i + value);
				i++;
			}
		}, 1000, 3000);
	}

	public static void main(String[] args) {
		new UdpClientTest("我是客户端1");
		new UdpClientTest("我是客户端2");
		new UdpClientTest("我是客户端3");
	}
}
