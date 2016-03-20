package com.nitian.socket.test;

import java.util.HashMap;
import java.util.Map;

import com.nitian.socket.core.Handler;
import com.nitian.socket.core.HandlerContext;
import com.nitian.socket.util.UtilHandler;

public class HandlerTest extends Handler {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", Integer.valueOf(12));
		HandlerContext handlerContext = new HandlerContext();
		handlerContext.setMap(map);
		HandlerTest handlerTest = new HandlerTest();
		handlerTest.handle();

		UtilHandler utilHandler = new UtilHandler();
		utilHandler.regist("/user", handlerTest);
		System.out.println("handler:" + utilHandler.get("/users"));
	}

	@Override
	public void handle() {
		// TODO Auto-generated method stub
		System.out.println("this is handle");
	}

}
