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
		while (true) {
			if (list.size() == 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log.info(LogType.error, this, "error+" + e.getMessage());
				}
			} else {
				T t = list.remove(list.size() - 1);
				handle(t);
				log.info(LogType.queue, this, "queueSize+" + list.size());
			}
		}

	}

	public abstract void handle(T t);
}
