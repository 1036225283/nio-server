package com.nitian.sort;

import com.nitian.util.Random;
import com.nitian.util.Second;
import com.nitian.util.Test;

/**
 * 插入排序
 * 
 * @author 1036225283
 *
 */
public class SortInsertion {

	/**
	 * 存放元素的数组
	 */
	private int[] array = new int[100];

	/**
	 * 数组的有效长度
	 */
	private int length = 0;

	/**
	 * 当前插入位置
	 */
	private int index = 0;

	/**
	 * 查找插入的位置
	 * 
	 * @param value
	 * @return
	 */
	public int find(int value) {
		index = 0;
		for (; index < length; index++) {
			if (value < array[index]) {
				break;
			}
		}
		return index;
	}

	/**
	 * 插入数据
	 * 
	 * @param value
	 */
	public void insert(int value) {
		find(value);
		int length_ = length;// 拷贝有效长度
		if (length < array.length) {
			for (; length_ > index; length_--) {
				array[length_] = array[length_ - 1];
			}
			array[index] = value;
			length = length + 1;
		}
	}

	/**
	 * 两个数组一样大 [1,3,4,5,5,6,33,44,90]
	 * 
	 * @param array
	 * @param value
	 */
	public static void sort(int[] array, int value) {
		// 第一步是寻找插入点
		int i = 0;
		for (; i < array.length - 1; i++) {
			if (value < array[i]) {
				break;
			}
		}
		// 第二步是插入数据
		for (; i < array.length; i++) {

		}
		System.out.println(i);
	}

	public static void main(String[] args) {
		int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 7, 8, 9 };
		SortInsertion insertion = new SortInsertion();
		for (int i = 0; i < 99; i++) {
			insertion.insert(i);
		}
		// insertion.array
		Test.show(insertion.array);
	}

	public SortInsertion() {
		// TODO Auto-generated constructor stub
	}

	public SortInsertion(int size) {
		// TODO Auto-generated constructor stub
		array = new int[size];
	}
}
