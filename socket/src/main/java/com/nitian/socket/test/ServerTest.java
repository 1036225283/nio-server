package com.nitian.socket.test;

import com.nitian.socket.tcp.ServerTcp;
import com.nitian.socket.test.action.LoginHandler;

public class ServerTest {

	public static void main(String[] args) {
		ServerTcp serverTcp = new ServerTcp(88);
		serverTcp.getApplicationContext().getHandlerFactory()
				.regist("/user/login", LoginHandler.class);
		serverTcp.start();
	}
}
