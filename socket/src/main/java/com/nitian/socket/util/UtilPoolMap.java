package com.nitian.socket.util;

import java.util.HashMap;
import java.util.Map;

public class UtilPoolMap extends UtilPool<Map<String, Object>> {

	public UtilPoolMap(Integer max, Integer total) {
		// TODO Auto-generated constructor stub
		initParam(max, total);
		initPool();
	}

	@Override
	protected Map<String, Object> factory() {
		// TODO Auto-generated method stub
		return new HashMap<String, Object>();
	}

	@Override
	protected void initValue(Map<String, Object> t) {
		// TODO Auto-generated method stub
		t.clear();
	}

}
