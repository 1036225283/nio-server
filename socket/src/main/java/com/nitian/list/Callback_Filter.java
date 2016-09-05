package com.nitian.list;

/**
 * filter callback
 * @author 1036225283
 *
 */
public interface Callback_Filter<K, V> {

	public boolean callback(K key, V value);
}
