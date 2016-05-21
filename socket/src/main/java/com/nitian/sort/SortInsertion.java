package com.nitian.sort;

import com.nitian.find.FindBinary;
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
	 * 插入数据
	 * 
	 * @param value
	 */
	public void insert(int value) {
		// index = FindSequential.findIndex(array, length, value);//顺序查找
		index = FindBinary.findIndex(array, length, value);// 二分查找
		int length_ = length;// 拷贝有效长度
		if (length < array.length) {
			for (; length_ > index; length_--) {
				array[length_] = array[length_ - 1];
			}
			array[index] = value;
			length = length + 1;
		}
	}

	public static void main(String[] args) {
		SortInsertion insertion = new SortInsertion();
		for (int i = 0; i < 99; i++) {
			insertion.insert(i);
		}
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
