package com.nitian.list;

import com.nitian.find.FindBinaryNode;

/**
 * 插入排序
 * 
 * @author 1036225283
 *
 */
public class ArrayList<K, V> implements List<K, V> {

	/**
	 * 存放元素的数组
	 */
	private Node<K, V>[] array;

	/**
	 * 数组的有效长度
	 */
	private int length = 0;

	/**
	 * 当前插入位置
	 */
	private int index = 0;

	/**
	 * 二分对象，负责二分查找，二分插入
	 */
	private FindBinaryNode<K, V> findBinary = new FindBinaryNode<K, V>();

	@SuppressWarnings("unchecked")
	public ArrayList() {
		// TODO Auto-generated constructor stub
		array = new Node[10000];
	}

	@SuppressWarnings("unchecked")
	public ArrayList(int size) {
		// TODO Auto-generated constructor stub
		array = new Node[size];
	}

	/**
	 * 添加key-value,排序插入
	 */
	@Override
	public void add(K key, V value) {
		// TODO Auto-generated method stub
		Node<K, V> node = new Node<K, V>();
		node.setKey(key);
		node.setValue(value);
		index = findBinary.findInsertIndex(array, key, length);// 二分查找

		int length_ = length;// 拷贝有效长度
		if (length < array.length) {
			for (; length_ > index; length_--) {
				array[length_] = array[length_ - 1];
			}
			array[index] = node;
			length = length + 1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		Node<K, V> node = findBinary.findNode(array, (Comparable<K>) key, length);
		if (node == null) {
			return null;
		} else {
			return node.getValue();
		}
	}

	@Override
	public int indexOf(V value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public K firstKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public K lastKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V firstValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V lastValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void each(Callback_Each<K, V> callback_Each) {
		// TODO Auto-generated method stub
		for (int i = 0; i < array.length; i++) {
			Node<K, V> node = array[i];
			callback_Each.callback(node.getKey(), node.getValue());
		}
	}

	@Override
	public List<K, V> filter(Callback_Filter<K, V> callback_Filter) {
		// TODO Auto-generated method stub
		List<K, V> list = new ArrayList<K, V>();
		for (int i = 0; i < array.length; i++) {
			Node<K, V> node = array[i];
			boolean result = callback_Filter.callback(node.getKey(), node.getValue());
			if (result) {
				list.add(node.getKey(), node.getValue());
			}
		}
		return list;
	}

	@Override
	public long count(Callback_Count<K, V> callback_Count) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public V findUnique(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
