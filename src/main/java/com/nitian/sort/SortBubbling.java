package com.nitian.sort;

/**
 * 冒泡排序
 * 
 * @author 1036225283
 *
 */
public class SortBubbling {

	/**
	 * 原始冒泡排序
	 * 
	 * @param array
	 */
	public static void sort(int[] array) {
		int tmp = 0;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] < array[j]) {
					tmp = array[j];
					array[j] = array[i];
					array[i] = tmp;
				}
			}
		}
	}

	/**
	 * 改进冒泡排序：每次比较出最大数和最小数，并置于两端，同时缩小比较范围
	 * 
	 * @param array
	 */
	public static void sort1(int[] array) {
		int max = array[0];// 最大值
		int min = array[0];// 最小值
		int right = array.length;// 右边界
		for (int left = 0; left < right - 1; left++) {
			for (int j = left + 1; j < right; j++) {
				if (min > array[j]) {
					min = array[j];
					continue;
				}
				if (max < array[j]) {
					max = array[j];
					continue;
				}
			}
			array[left] = min;
			array[right - 1] = max;
			right = right - 1;
			if (right - left <= 1) {
				break;
			}
		}
	}

	/**
	 * 打算模仿大话数据结构中的算法，非常狗屎
	 * 
	 * @param array
	 */
	public static void sort2(int[] array) {
		int tmp = 0;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j + 1]) {
					tmp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = tmp;
				}
			}
			// show(array);
		}
	}

	/**
	 * 简单选择排序
	 * 
	 * @param array
	 */
	public static void simpleChoose(int[] array) {
		int min = 0;
		int tmp = 0;
		for (int i = 0; i < array.length; i++) {
			min = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[min] > array[j]) {
					min = j;
				}
			}
			if (i != min) {
				tmp = array[i];
				array[i] = array[min];
				array[min] = tmp;
			}
		}
	}

	public static void show(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println("");
	}

}
