package com.nitian.list;

/**
 * key-value node
 * @author 1036225283
 *
 */
public class Node<K, V> {

	/**
	 * key
	 */
	private K key;

	/**
	 * value
	 */
	private V value;

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

}
