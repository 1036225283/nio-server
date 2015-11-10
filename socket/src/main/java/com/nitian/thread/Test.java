package com.nitian.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.nitian.tcp.TcpClient;

public class Test {

	public static void main(String[] args) {
		// 单个线程
		ExecutorService newSingleThreadExecutor = Executors
				.newSingleThreadExecutor();
		// 固定数量
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(12);

		// 可缓存的
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

		// 大小无限
		ExecutorService newScheduledThreadPool = Executors
				.newScheduledThreadPool(13);

		newScheduledThreadPool.execute(new Hello());

		// 关闭线程池
		newScheduledThreadPool.shutdown();
	}
}
