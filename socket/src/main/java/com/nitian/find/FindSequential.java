package com.nitian.find;

/**
 * 顺序查找
 * 
 * @author 1036225283
 *
 */
public class FindSequential {

	public static int findIndex(int[] array, int length, int value) {
		int index = 0;
		for (; index < length; index++) {
			if (value < array[index]) {
				break;
			}
		}
		return index;
	}

}
