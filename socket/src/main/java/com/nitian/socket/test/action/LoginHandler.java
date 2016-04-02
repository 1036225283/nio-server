package com.nitian.socket.test.action;

import java.util.Map;

import com.nitian.socket.core.Handler;

public class LoginHandler extends Handler {

	@Override
	public void handle(Map<String, String> map) {
		// TODO Auto-generated method stub
		System.out.println(this + "^^" + map);
		System.out.println("this is login");
	}

}
