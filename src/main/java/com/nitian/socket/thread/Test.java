package com.nitian.socket.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	public static void main(String[] args) {
		// 单个线程
		ExecutorService newSingleThreadExecutor = Executors
				.newSingleThreadExecutor();
		// 固定数量
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);

		// 可缓存的
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

		// 大小无限
		ExecutorService newScheduledThreadPool = Executors
				.newScheduledThreadPool(13);
		Hello hello = new Hello();
		Hello hello2 = new Hello();
		Hello hello3 = new Hello();
		newFixedThreadPool.execute(hello);
		newFixedThreadPool.execute(hello2);
		newFixedThreadPool.execute(hello3);
		// 关闭线程池
		// newCachedThreadPool.shutdown();
	}
}
