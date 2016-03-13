package com.nitian.tcp;

import java.io.IOException;
import java.net.Socket;

import com.nitian.ApplicationContext;
import com.nitian.util.UtilPoolByte;

/**
 * 线程读数据
 * 
 * @author 1036225283
 *
 */
public class ThreadRead extends Thread {

	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();

	private Socket socket;

	public ThreadRead(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		byte[] bs = applicationContext.getPoolByte().lend();
		try {
			int size = socket.getInputStream().read(bs);
			System.out.println("size: " + size);
			System.out.println(new String(bs));
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
