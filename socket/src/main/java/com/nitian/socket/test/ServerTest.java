package com.nitian.socket.test;

import com.nitian.socket.core.Handler;
import com.nitian.socket.tcp.ServerTcp;

public class ServerTest {

	public static void main(String[] args) {
		ServerTcp serverTcp = new ServerTcp(88);
		serverTcp.getApplicationContext().getUtilHandler()
				.regist("/user/login", new Handler() {

					@Override
					public void handle() {
						// TODO Auto-generated method stub
						System.out.println("this is handler");
					}
				});
		serverTcp.start();
	}
}
