package com.nitian.socket.util;

import java.util.ArrayList;
import java.util.List;

public abstract class UtilQueue<T> extends Thread {

	private List<T> list = new ArrayList<T>();// 消息队列

	public synchronized void push(T t) {
		list.add(t);
		notify();
	}

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				if (list.size() == 0) {
					System.out.println("list size" + list.size());
					wait();
				} else {
					T t = list.remove(0);
					handle(t);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public abstract void handle(T t);
}
