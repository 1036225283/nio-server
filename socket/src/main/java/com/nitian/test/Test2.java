package com.nitian.test;

import com.nitian.client.TcpSocketClient;

public class Test2 {

	public static void main(String[] args) {
		TcpSocketClient k = new TcpSocketClient("luck");
		k.start();
	}
}
