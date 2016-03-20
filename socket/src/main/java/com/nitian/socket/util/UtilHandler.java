package com.nitian.socket.util;

import com.nitian.socket.core.DefaultHandler;
import com.nitian.socket.core.Handler;

/**
 * url-handler处理器
 * 
 * @author 1036225283
 *
 */
public class UtilHandler extends UtilRegister<Handler> {

	@Override
	public Handler get(String key) {
		// TODO Auto-generated method stub
		Handler handler = super.get(key);
		if (handler == null) {
			return new DefaultHandler();
		} else {
			return handler;
		}
	}
}
