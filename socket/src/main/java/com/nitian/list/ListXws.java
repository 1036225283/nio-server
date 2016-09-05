package com.nitian.list;

public interface ListXws<K, V> {

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void add(K key, V value);

	/**
	 * 根据索引获取值
	 * @param index
	 * @return
	 */
	public Object get(K key);

	/**
	 * 获取一个元素第一次出现的索引
	 * @param object
	 * @return
	 */
	public int indexOf(V value);

	/**
	 * 获取第一key
	 * @return
	 */
	public K firstKey();

	/**
	 * 获取最后一个key
	 * @return
	 */
	public K lastKey();

	/**
	 * 获取第一个value
	 * @return
	 */
	public V firstValue();

	/**
	 * 获取
	 * @return
	 */
	public V lastValue();

	/**
	 * 遍历
	 * @param callback_Each
	 */
	public void each(Callback_Each<K, V> callback_Each);

	/**
	 * filter
	 * @param callback_Filter
	 * @return
	 */
	public ListXws<K, V> filter(Callback_Filter<K, V> callback_Filter);

	/**
	 * 按条件统计数量
	 * @param callback_Count
	 * @return
	 */
	public long count(Callback_Count<K, V> callback_Count);

	/**
	 * 查询惟一key对应的value
	 * @param key
	 * @return
	 */
	public V findUnique(K key);

	public void show();

	public int size();

}
