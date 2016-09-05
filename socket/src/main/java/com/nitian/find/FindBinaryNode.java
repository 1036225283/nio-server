package com.nitian.find;

import com.nitian.list.Node;
import com.nitian.util.log.LogManager;

/**
 * 二分查找
 * 
 * @author 1036225283
 *
 */
public class FindBinaryNode<K, V> {

	static LogManager logManager = LogManager.getInstance();

	public static void main(String[] args) throws InterruptedException {
		logManager.dateInfo("start");
		int[] ints = new int[100000];
		@SuppressWarnings("unchecked")
		Node<Integer, String>[] array = new Node[ints.length];
		for (int i = 0; i < ints.length; i++) {
			// ints[i] = (int) (Math.random() * 1000);
			ints[i] = i;
			Node<Integer, String> node = new Node<Integer, String>();
			node.setKey(i);
			node.setValue("value" + i);
			array[i] = node;
		}
		logManager.dateInfo("init");
		FindBinaryNode<Integer, String> test = new FindBinaryNode<Integer, String>();
		// test.findNode(array, 5000);
		int[] intsb = new int[] { 1, 3, 5, 9, 10, 11, 13, 13, 13 };
		@SuppressWarnings("unchecked")
		Node<Integer, String>[] array1 = new Node[intsb.length];
		for (int i = 0; i < intsb.length; i++) {
			Node<Integer, String> node = new Node<Integer, String>();
			node.setKey(intsb[i]);
			node.setValue("value" + intsb[i]);
			array1[i] = node;
		}
		int index = test.findInsertIndex(array1, 14, array1.length);
		System.out.println("插入位置：" + index);
		logManager.dateInfo("end");
	}

	public Node<K, V> findNode(Node<K, V>[] array, Comparable<K> key, int length) {
		int nMin = 0;// 区间左边界
		int nMax = length;// 区间右边界
		int nPrintIndex = 0;
		int nHalf = 0;// 二分之一索引(坐标)
		// 根据左边界索引，右边界索引，二分之一索引，获取索引对应的值
		K kHalf = array[nHalf].getKey();
		while (nMax - nMin > 0) {// 判断索引是否合理
			System.out.println("查找次数：" + nPrintIndex++);
			nHalf = nMin + (nMax - nMin) / 2;// 折半
			System.out.println("index：" + nHalf);
			kHalf = array[nHalf].getKey();
			int nCompareResult = 0;
			if (key instanceof String) {
				String strSrc = (String) key;
				String strDst = (String) kHalf;
				nCompareResult = compareString(strSrc, strDst);
			} else {
				nCompareResult = key.compareTo(kHalf);
			}
			if (nCompareResult == 0) {
				System.out.println("ok,good,find: " + nHalf);
				return array[nHalf];
			} else if (nCompareResult == 1) {
				nMin = nHalf;// 重新设置左边界
			} else if (nCompareResult == -1) {
				nMax = nHalf;// 重新设置右边界
			}
		}
		return null;
	}

	/**
	 * 获取插入位置
	 * @param array
	 * @param key
	 * @param length
	 * @return
	 */
	public int findInsertIndex(Node<K, V>[] array, K key, int length) {
		if (length == 0) {
			return 0;
		}
		int nMin = 0;// 区间左边界
		int nMax = array.length - 1;// 区间右边界
		int nPrintIndex = 0;
		int nHalf = 0;// 二分之一索引(坐标)
		// 根据左边界索引，右边界索引，二分之一索引，获取索引对应的值
		K kHalf = array[nHalf].getKey();
		while (nMax - nMin > 1) {// 判断索引是否合理
			System.out.println("查找次数：" + nPrintIndex++);
			nHalf = nMin + (nMax - nMin) / 2;// 折半
			System.out.println("index：" + nHalf);
			kHalf = array[nHalf].getKey();
			int nCompareResult = compare(key, kHalf);
			if (nCompareResult == 0) {
				System.out.println("ok,good,find: " + nHalf);
				return nHalf;
			} else if (nCompareResult == 1) {
				nMin = nHalf;// 重新设置左边界
			} else if (nCompareResult == -1) {
				nMax = nHalf;// 重新设置右边界
			}
		}

		K kMin = array[nMin].getKey();
		K kMax = array[nMax].getKey();
		if (compare(key, kMax) != 1 && compare(key, kMin) != -1) {
			return nMin + 1;
		} else if (compare(key, kMax) == 1) {
			return nMax + 1;
		} else if (compare(key, kMin) == -1) {
			return nMin;
		} else {
			return -1;
		}
	}

	private int compare(K kSrc, K kDst) {
		int nCompareResult = 0;
		if (kSrc instanceof String) {
			String strSrc = (String) kSrc;
			String strDst = (String) kDst;
			nCompareResult = compareString(strSrc, strDst);
		} else {
			@SuppressWarnings("unchecked")
			Comparable<K> comparable = (Comparable<K>) kSrc;
			nCompareResult = comparable.compareTo(kDst);
		}
		return nCompareResult;
	}

	/**
	 * 如果等于true，否则false
	 * @param strSrc
	 * @param strDst
	 * @return
	 */
	private static int compareString(String strSrc, String strDst) {
		if (strSrc.length() > strDst.length()) {
			return 1;
		} else if (strSrc.length() < strDst.length()) {
			return -1;
		}
		char[] strSrcs = strSrc.toCharArray();
		char[] strDsts = strDst.toCharArray();
		for (int i = 0; i < strDsts.length; i++) {
			if (strSrcs[i] > strDsts[i]) {
				return 1;
			} else if (strSrcs[i] < strDsts[i]) {
				return -1;
			} else {
				continue;
			}
		}
		return 0;
	}
}
