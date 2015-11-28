package com.nitian.udp;

import java.util.Timer;
import java.util.TimerTask;

public class UdpClientTest {

	public UdpClientTest() {
		UdpClient client = new UdpClient();
		timer(client);
	}

	public void timer(final UdpClient client) {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				String write = "abcdefg123";
				System.out.println(write.length());
				client.push(write);
			}
		}, 10000, 5000);
	}

	public static void main(String[] args) {
		UdpClientTest client = new UdpClientTest();
	}
}
