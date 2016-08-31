package com.nitian.list;

import com.nitian.find.FindBinary;
import com.nitian.util.Test;

/**
 * 插入排序
 * 
 * @author 1036225283
 *
 */
public class List_Array implements ListXws {

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
	@Override
	public void add(int value) {
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
		List_Array insertion = new List_Array();
		for (int i = 0; i < 99; i++) {
			insertion.add(i);
		}
		Test.show(insertion.array);
	}

	public List_Array() {
		// TODO Auto-generated constructor stub
	}

	public List_Array(int size) {
		// TODO Auto-generated constructor stub
		array = new int[size];
	}

	@Override
	public Object get(int index) {
		// TODO Auto-generated method stub
		return array[index];
	}

	@Override
	public Object first() {
		// TODO Auto-generated method stub
		if (size() == 0) {
			return null;
		}
		return array[0];
	}

	@Override
	public Object last() {
		// TODO Auto-generated method stub
		if (size() == 0) {
			return null;
		}
		return array[size() - 1];
	}

	@Override
	public void each(Callback_Each callback_Each) {
		// TODO Auto-generated method stub
		for (int i = 0; i < array.length; i++) {
			callback_Each.callback(i, array[i]);
		}
	}

	@Override
	public ListXws filter(Callback_Filter callback_Filter) {
		// TODO Auto-generated method stub
		List_Array list = new List_Array();
		for (int i = 0; i < array.length; i++) {
			boolean result = callback_Filter.callback(i, array[i]);
			if (result) {
				list.add(array[i]);
			}
		}
		return list;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		System.out.println(array.toString());
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return length;
	}

	@Override
	public int indexOf(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
}
