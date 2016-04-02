package com.nitian.socket.util;

import com.nitian.socket.core.Handler;

/**
 * url-handler处理器
 * 
 * @author 1036225283
 *
 */
public class HandlerFactory extends UtilRegister<Class<?>> {

	@Override
	public Class<Handler> get(String key) {
		// TODO Auto-generated method stub
		Class<Handler> handlerClass = (Class<Handler>) get(key);
		if (handlerClass == null) {
			return get("default");
		} else {
			return handlerClass;
		}
	}
}
