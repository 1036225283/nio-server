package com.nitian.util;

import com.nitian.sort.SortBubbling;
import com.nitian.sort.SortInsertion;

public class Test {

	public static void main(String[] args) {
		Second.getSecond("初始化");
		int[] array = Random.create(100000);
		int[] array2 = copy(array);
		int[] array3 = copy(array);
		int[] array4 = copy(array);
		Second.getSecond("create array");
		SortBubbling.sort(array);
		Second.getSecond("bubbling sort");
		SortBubbling.sort1(array2);
		Second.getSecond("bubbling sort1");
		SortBubbling.simpleChoose(array3);
		Second.getSecond("bubbling sort2");
		SortInsertion insertion = new SortInsertion(array4.length);
		for (int i = 0; i < array4.length; i++) {
			insertion.insert(array4[i]);
		}
		Second.getSecond("insert sort");
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
