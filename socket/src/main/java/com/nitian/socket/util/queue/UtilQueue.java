package com.nitian.socket.util.queue;

import java.util.ArrayList;
import java.util.List;

import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

public abstract class UtilQueue<T> implements Runnable {

	private List<T> list = new ArrayList<T>();// 消息队列

	protected LogManager log = LogManager.getInstance();

	public synchronized void push(T t) {
		list.add(t);
		notify();
	}

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				log.info(LogType.thread, this, Thread.currentThread()
						.toString());
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
