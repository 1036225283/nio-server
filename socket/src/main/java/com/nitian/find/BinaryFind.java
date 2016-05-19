package com.nitian.find;

import java.util.Date;

/**
 * 二分查找
 * 
 * @author 1036225283
 *
 */
public class BinaryFind {

	public static void main(String[] args) throws InterruptedException {
		long dates = new Date().getTime();

		int[] ints = new int[100000];

		for (int i = 0; i < ints.length; i++) {
			// ints[i] = (int) (Math.random() * 1000);
			ints[i] = i;
		}
		// find(ints, 5003);
		int[] intsb = new int[] { 1, 3, 5, 9, 10, 11, 13, 13, 13 };
		int index = findIndex(intsb, 14, intsb.length);
		System.out.println("插入位置：" + index);
		long datee = new Date().getTime();
		System.out.println("时间：" + (datee - dates));
	}

	/**
	 * 二分查找单个值
	 * 
	 * @param ints
	 * @param value
	 */
	public static void find(int[] ints, int value) {
		int min = 0;// 区间左边界
		int max = ints.length;// 区间右边界
		int index = 0;
		int foldIndex = 0;// 二分值索引(坐标)
		int interval = 0;// 区间
		int foldValue = 0;// 二分值
		while (max - min > 0) {
			System.out.println("查找次数：" + index++);
			interval = max - min;
			foldIndex = min + interval / 2;
			System.out.println("index：" + foldIndex);
			foldValue = ints[foldIndex];
			if (value == foldValue) {
				System.out.println(foldIndex);
				return;
			} else if (value > foldValue) {
				min = foldValue;// 重新设置左边界
			} else if (value < foldValue) {
				max = foldValue;// 重新设置右边界
			}
		}
	}

	/**
	 * 二分查找插入位置
	 * 
	 * @param ints
	 * @param value
	 * @param length
	 *            :数据长度
	 */
	public static int findIndex(int[] ints, int value, int length) {
		int min = 0;// 区间左边界
		int max = length - 1;// 区间右边界
		int index = 0;
		int foldIndex = 0;// 二分值索引(坐标)
		int interval = 0;// 区间
		int foldValue = 0;// 二分值
		while (max - min > 1) {
			System.out.println("查找次数：" + index++);
			interval = max - min;
			foldIndex = min + interval / 2;
			System.out.println("index：" + foldIndex);
			foldValue = ints[foldIndex];
			if (value == foldValue) {
				System.out.println(foldIndex);
				return foldIndex;
			} else if (value > foldValue) {
				min = foldIndex;// 重新设置左边界
			} else if (value < foldValue) {
				max = foldIndex;// 重新设置右边界
			}
		}
		System.out.println("-----------");
		System.out.println(max);
		System.out.println(min);
		if (value <= ints[max] && value >= ints[min]) {
			return min + 1;
		} else if (value > ints[max]) {
			return max + 1;
		} else if (value < ints[min]) {
			return min;
		} else {
			return -1;
		}
	}

	/**
	 * 固定长度插入排序
	 * 
	 * @param ints
	 * @return
	 */
	public static int[] sort(int[] ints) {
		int[] tmp = new int[ints.length];

		return tmp;
	}

}
