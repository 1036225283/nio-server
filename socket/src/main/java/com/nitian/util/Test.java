package com.nitian.util;

import com.nitian.list.List_Linked;
import com.nitian.list.List_LinkedDB;
import com.nitian.sort.SortBubbling;
import com.nitian.sort.SortInsertion;

public class Test {

	public static void main(String[] args) {
		Second.getSecond("初始化");
		int[] array = Random.create(10000);
		int[] array2 = copy(array);
		int[] array3 = copy(array);
		int[] array4 = copy(array);
		int[] array5 = copy(array);
		int[] array6 = copy(array);
		Second.getSecond("create array");
		SortBubbling.sort(array);
		Second.getSecond("原始冒泡排序");
		SortBubbling.sort1(array2);
		Second.getSecond("改进冒泡排序");
		SortBubbling.simpleChoose(array3);
		Second.getSecond("简单选择排序");
		SortInsertion insertion = new SortInsertion(array4.length);
		for (int i = 0; i < array4.length; i++) {
			insertion.insert(array4[i]);
		}
		Second.getSecond("插入排序");
		List_Linked linkedList = new List_Linked();
		for (int i = 0; i < array5.length; i++) {
			linkedList.insert(array5[i]);
		}
		Second.getSecond("单链表排序");

		List_LinkedDB doubleList = new List_LinkedDB();
		for (int i = 0; i < array6.length; i++) {
			doubleList.insert(array6[i]);
		}
		Second.getSecond("双链表排序");
		doubleList.show();
		// linkedList.show();
		// System.out.println();
		System.out.println(array5.length);
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
