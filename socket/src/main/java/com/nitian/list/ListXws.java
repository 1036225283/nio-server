package com.nitian.list;

public interface ListXws {

	/**
	 * 向单向列表添加值
	 * @param value
	 */
	public void add(int value);

	/**
	 * 根据索引获取值
	 * @param index
	 * @return
	 */
	public Object get(int index);

	/**
	 * 获取一个元素第一次出现的索引
	 * @param object
	 * @return
	 */
	public int indexOf(Object object);

	/**
	 * 返回第一个元素
	 * @return
	 */
	public Object first();

	/**
	 * 获取最后一个值
	 * @return
	 */
	public Object last();

	/**
	 * 遍历
	 * @param callback_Each
	 */
	public void each(Callback_Each callback_Each);

	/**
	 * filter
	 * @param callback_Filter
	 * @return
	 */
	public ListXws filter(Callback_Filter callback_Filter);

	public void show();

	public int size();

}
