package com.nitian.util;

import java.util.Date;

public class Second {

	private static long second = new Date().getTime();

	public static void getSecond(String note) {
		long tmp = new Date().getTime();
		System.out.println(note + ":" + (tmp - second));
		second = tmp;
	}
}
