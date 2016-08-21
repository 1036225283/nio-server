package com.nitian.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nitian.util.Random;
import com.nitian.util.log.LogManager;

public class Test {

	static LogManager logManager = LogManager.getInstance();

	public Map<String, String> map = new HashMap<String, String>();
	public List_Linked list = new List_Linked();
	public int[] array = Random.create(20000);
	public List<Integer> javaList = new ArrayList<Integer>();
	public ListXws list_Array = new List_Array(array.length);

	public Test() {
		// TODO Auto-generated constructor stub
		logManager.putType("info");
		LogManager.setFileLog(false);
	}

	public static void main(String[] args) throws InterruptedException {
		Test test = new Test();
		// test.initMap();
		test.initJavaList();
		test.initListLinked();
		test.initList_Array();
		// test.eachMap();
		test.testSelectJavaList();
		test.testSelectListlinked();
		test.testSelectList_Array();
		System.out.println("this is end");
	}

	public void initList_Array() {
		logManager.dateInfo("info", Test.class, null);
		for (int i = 0; i < array.length; i++) {
			list_Array.add(array[i]);
		}
		logManager.dateInfo("info", Test.class, "list_Array insert end");
	}

	public void testSelectList_Array() {
		logManager.dateInfo("info", Test.class, null);
		for (int i = 0; i < array.length; i++) {
			list_Array.get(i);
		}
		logManager.dateInfo("info", Test.class, "list_Array select end");

	}

	public void initMap() {
		logManager.dateInfo("info", Test.class, null);
		for (int i = 0; i < array.length; i++) {
			map.put("" + array[i], "" + array[i]);
		}
		logManager.dateInfo("info", Test.class, "Map insert end");
	}

	public void initJavaList() {
		logManager.dateInfo("info", Test.class, null);
		for (int i = 0; i < array.length; i++) {
			javaList.add(array[i]);
		}
		logManager.dateInfo("info", Test.class, "Java List insert end");
	}

	/**
	 * 初始化单向链表，同时测试插入时间
	 */
	public void initListLinked() {
		logManager.dateInfo("info", Test.class, null);
		for (int i = 0; i < array.length; i++) {
			list.add(array[i]);
		}
		logManager.dateInfo("info", Test.class, "ListLinked insert end");
	}

	/**
	 * 遍历map，同时测试遍历时间
	 */
	public void eachMap() {
		logManager.dateInfo("info", Test.class, null);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			entry.getValue();
			// System.out.println("key= " + entry.getKey() + " and value= " +
			// entry.getValue());
		}
		logManager.dateInfo("info", Test.class, "eachMap insert end");
	}

	public void eachListLinked() {
		logManager.dateInfo("info", Test.class, null);
		list.each(new Callback_Each() {

			@Override
			public void callback(int index, Object value) {
				// TODO Auto-generated method stub
				// System.out.println("listlinked value = " + value);
			}
		});
		logManager.dateInfo("info", Test.class, "eachListLinked end");
	}

	public void eachJavaList() {
		logManager.dateInfo("info", Test.class, null);
		for (int i = 0; i < javaList.size(); i++) {
			int test = javaList.get(i);
		}
		logManager.dateInfo("info", Test.class, "eachJavaList end");
	}

	public void testSelectMap() {
		logManager.dateInfo("info", Test.class, null);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			entry.getValue();
			// System.out.println("key= " + entry.getKey() + " and value= " +
			// entry.getValue());
		}
		logManager.dateInfo("info", Test.class, "eachMap select end");
	}

	public void testSelectListlinked() {
		logManager.dateInfo("info", Test.class, null);
		for (int i = 0; i < array.length; i++) {
			list.get(i);
		}
		logManager.dateInfo("info", Test.class, "select List end");
	}

	public void testSelectJavaList() {
		logManager.dateInfo("info", Test.class, null);
		for (int i = 0; i < array.length; i++) {
			javaList.get(i);
		}
		logManager.dateInfo("info", Test.class, "selectjavaList end");
	}
}
