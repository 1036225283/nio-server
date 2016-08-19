package com.nitian.list;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.nitian.sort.SortInsertion;
import com.nitian.util.Random;
import com.nitian.util.log.LogManager;

public class Test {

	static LogManager logManager = LogManager.getInstance();

	public static void main(String[] args) throws InterruptedException {
		Test test = new Test();
		test.test();
	}

	/**
	 * 测试链表查询的速度和hashMap查询的速度
	 */
	public void test2(){
		//
		
		List<String> list = new ArrayList<String>();
		list.add("ad");
		list.add(12, "dd");
		list.get(1);
		list.size();
		list.remove(1);
		list.remove(1);
		list.indexOf(12);
		list.lastIndexOf(12);
	}
	
	/**
	 * 测试hashMap和array插入排序的比较
	 */
	public void test() {
		logManager.dateInfo("info", Test.class, "init");
		long start = new Date().getTime();
		logManager.setFileLog(false);
		logManager.putType("info");
		int[] array = Random.create(10000);
		//测试hashMap插入数据
		logManager.dateInfo("info", Test.class, "start");
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < array.length; i++) {
			map.put("" + array[i], "" + array[i]);
		}
		logManager.dateInfo("info", Test.class, "end");
		//测试插入排序
		logManager.dateInfo("info", Test.class, "插入start");
		SortInsertion insertion = new SortInsertion(array.length);
		for (int i = 0; i < array.length; i++) {
			insertion.insert(array[i]);
		}
		logManager.dateInfo("info", Test.class, "插入end");
		long end = new Date().getTime();
		System.out.println(end - start);
	}
}
