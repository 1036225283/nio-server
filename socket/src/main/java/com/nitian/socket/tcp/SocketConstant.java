package com.nitian.socket.tcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketConstant {

	/**
	 * <用户识别,用户信息>
	 */
	public static Map<String, SocketUser> map = new HashMap<String, SocketUser>();

	/**
	 * 未认证用户信息
	 */
	public static List<SocketUser> list = new ArrayList<SocketUser>();

	/**
	 * 固定数量线程池
	 */
	public static ExecutorService read = Executors.newFixedThreadPool(100);

	/**
	 * 固定数量线程池
	 */
	public static ExecutorService write = Executors.newFixedThreadPool(20);
}
