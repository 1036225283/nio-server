package com.nitian.socket.util;

import com.nitian.socket.core.HandlerContext;

public class UtilPoolHandlerContext extends UtilPool<HandlerContext> {

	public UtilPoolHandlerContext(Integer max, Integer total) {
		// TODO Auto-generated constructor stub
		initParam(max, total);
		initPool();
	}

	@Override
	protected HandlerContext factory() {
		// TODO Auto-generated method stub
		return new HandlerContext();
	}

	@Override
	protected void initValue(HandlerContext t) {
		// TODO Auto-generated method stub
		t.initValue();
	}

}
