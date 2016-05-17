package com.nitian.util;

public class Random {

	public static int[] create(int length) {
		int[] tmp = new int[length];
		for (int i = 0; i < length; i++) {
			tmp[i] = (int) (Math.random() * 10000);
		}
		return tmp;
	}

	public static void main(String[] args) {
		System.out.println(create(1000));
	}
}
