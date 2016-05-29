package com.nitian.list;

public interface BigListInterface {

	/**
	 * 获取最大值
	 * 
	 * @return
	 */
	public long Max();

	/**
	 * 获取最小值
	 * 
	 * @return
	 */
	public long min();

	/**
	 * 获取长度
	 * 
	 * @return
	 */
	public long length();

	/**
	 * 插入值
	 * 
	 * @param value
	 */
	public void insert(int value);

	/**
	 * 获取index的值
	 * 
	 * @param index
	 * @return
	 */
	public int index(int index);

	/**
	 * 获取第一个值
	 * 
	 * @return
	 */
	public int first();

	/**
	 * 获取最后一个值
	 * 
	 * @return
	 */
	public int last();

	
}
