package com.nitian.util;

import com.nitian.sort.Bubbling;

public class Test {

	public static void main(String[] args) {
		Second.getSecond("初始化");
		int[] array = Random.create(1000000);
		int[] array2 = copy(array);
		int[] array3 = copy(array);
		Second.getSecond("create array");
		Bubbling.sort(array);
		Second.getSecond("bubbling sort");
		Bubbling.sort1(array2);
		Second.getSecond("bubbling sort1");
		Bubbling.simpleChoose(array3);
		Second.getSecond("bubbling sort2");
		System.out.println(array3.length);
		// for (int i = 0; i < array2.length; i++) {
		// System.out.println(array[i]);
		// }
	}

	public static int[] copy(int[] array) {
		int[] tmp = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			tmp[i] = array[i];
		}
		return tmp;
	}

	public static void show(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("");
	}
}
