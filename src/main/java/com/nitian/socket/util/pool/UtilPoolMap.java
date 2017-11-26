package com.nitian.socket.util.pool;

import java.util.HashMap;
import java.util.Map;

/**
 * 解析读消息池(map)
 * 
 * @author 1036225283
 *
 */
public class UtilPoolMap extends UtilPool<Map<String, Object>> {

	public UtilPoolMap(Integer max, Integer total) {
		// TODO Auto-generated constructor stub
		initParam(max, total);
		initPool();
	}

	@Override
	protected synchronized Map<String, Object> factory() {
		// TODO Auto-generated method stub
		return new HashMap<String, Object>();
	}

	@Override
	protected synchronized void initValue(Map<String, Object> t) {
		// TODO Auto-generated method stub
		t.clear();
	}

}
