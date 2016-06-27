package com.nitian.socket.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.nitian.socket.tcp.ServerTcp;
import com.nitian.socket.test.handler.ExitHandler;
import com.nitian.socket.test.handler.LoginHandler;

public class ServerTest {

	public static void main(String[] args) {
		try {
			ServerTcp serverTcp = new ServerTcp(88);
			serverTcp.getApplicationContext().getHandlerFactory()
					.regist("/user/login", LoginHandler.class)
					.regist("/exit", ExitHandler.class);
			serverTcp.start();
		} catch (Exception e) {
			// TODO: handle exception
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(baos));
			String exception = baos.toString();
			System.out.println("baos:" + exception);
			return;
		}

	}
}
