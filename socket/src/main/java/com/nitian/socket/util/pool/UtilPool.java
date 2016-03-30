package com.nitian.socket.util.pool;

import java.util.LinkedList;
import java.util.List;

public abstract class UtilPool<T> {

	private List<T> list = new LinkedList<T>();
	private int total = 100;// 缓存池总大小
	private int max = 1000;// 缓存容量极值

	/**
	 * 抽象的工厂函数
	 */
	protected abstract T factory();

	/**
	 * 抽象的初始化函数
	 */
	protected abstract void initValue(T t);

	/**
	 * 初始化参数
	 * 
	 * @param max
	 * @param total
	 */
	public void initParam(Integer max, Integer total) {
		if (max != null) {
			this.max = max;
		}
		if (total != null) {
			this.total = total;
		}
	}

	/**
	 * 初始化池
	 */
	public void initPool() {
		for (int i = 0; i < this.total; i++) {
			T t = factory();
			initValue(t);
			list.add(t);
		}
	}

	/**
	 * 从池借对象
	 * 
	 * @return
	 */
	public T lend() {
		System.out.println("------action:lend,poolSize:" + (list.size() - 1));
		T result = null;
		if (list.size() > 0) {
			result = list.remove(0);
			return result;
		} else if (max > total) {
			total = total + 1;
			result = factory();
			initValue(result);
			return result;
		} else {
			return null;
		}
	}

	/**
	 * 向池还对象
	 * 
	 * @param t
	 */
	public void repay(T t) {
		initValue(t);
		list.add(t);
		System.out.println("------action:repay,poolSize:" + list.size());
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

}
