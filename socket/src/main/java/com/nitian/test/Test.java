package com.nitian.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nitian.client.TcpSocketClient;

public class Test {

	private List<TcpSocketClient> clients = new ArrayList<TcpSocketClient>();
	
	public void test(){
		Date startTime = new Date();
		System.out.println();
		for (int i = 0; i < 2000; i++) {
			TcpSocketClient client = new TcpSocketClient("duck");
			clients.add(client);
			client.start();
		}
		Date endTime = new Date();
		System.out.println("this is ok");
		System.out.println((endTime.getTime()-startTime.getTime()));
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		test.test();
		
	}
}
