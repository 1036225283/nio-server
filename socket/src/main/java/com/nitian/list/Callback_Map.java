package com.nitian.list;

/**
 * map callback
 * @author 1036225283
 *
 */
public interface Callback_Map<K, V> {

	public Object callback(K key, V value);
}
