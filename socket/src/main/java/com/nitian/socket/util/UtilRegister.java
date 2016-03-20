package com.nitian.socket.util;

import java.util.HashMap;
import java.util.Map;

public class UtilRegister<T> {

	private Map<String, Object> map = new HashMap<String, Object>();

	private String username = "admin";

	private String password = "admin123";

	public void regist(String key, T t) {
		if (map.containsKey(key)) {
			throw new RuntimeException(t.toString() + " the key is registered");
		}
		map.put(key, t);
	}

	public void regist(String key, T t, String username, String password) {
		if (this.username.equals(username) && this.password.equals(password)) {
			map.put(key, t);
		} else {
			throw new RuntimeException(t.toString()
					+ " username or password is error");
		}
	}

	@SuppressWarnings("unchecked")
	public T get(String key) {
		return (T) map.get(key);
	}

}
