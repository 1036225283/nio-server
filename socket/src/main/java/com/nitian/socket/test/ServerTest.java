package com.nitian.socket.test;

import com.nitian.socket.tcp.ServerTcp;
import com.nitian.socket.test.handler.ExitHandler;
import com.nitian.socket.test.handler.LoginHandler;

public class ServerTest {

	public static void main(String[] args) {
		ServerTcp serverTcp = new ServerTcp(88);
		serverTcp.getApplicationContext().getHandlerFactory()
				.regist("/user/login", LoginHandler.class)
				.regist("/exit", ExitHandler.class);
		serverTcp.start();
	}
}
