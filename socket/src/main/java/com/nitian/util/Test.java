package com.nitian.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		UtilPoolByte poolByte = new UtilPoolByte(20, 2, null);
		byte[] bs = poolByte.lend();
		System.out.println(bs.length);
	}

	public static void testPoolMap() {
		UtilPoolMap map = new UtilPoolMap(100, null);
		map.getTotal();
		Map<String, Object> m = map.lend();
		System.out.println(m);
		m.put("hello", "world");
		System.out.println(m.get("hello"));
	}

	public static void testList() {
		List<String> list = new LinkedList<String>();
		list.add("aaaa");
		list.add("bbbb");
		String c = list.remove(0);
		System.out.println(c);
		System.out.println(list.size());
	}
}
