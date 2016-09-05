package com.nitian.list;

/**
 * count callback
 * @author 1036225283
 *
 */
public interface Callback_Count<K, V> {

	public boolean callback(K key, V value);
}
