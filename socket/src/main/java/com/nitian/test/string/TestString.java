package com.nitian.test.string;

public class TestString {

	public static void main(String[] args) {
		test1();
	}

	/**
	 * 测试new String(byte b,length,offset)
	 * 
	 * @param value
	 */
	public static void test1() {

		String test = "abcde";
		byte[] b = test.getBytes();
		System.out.println(new String(b, 1, 2));
	}
}
