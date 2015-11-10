package com.nitian.tcp;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 多线程socket写数据
 * 
 * @author 1036225283
 *
 */
public class TcpWrite extends Thread {

	private Socket socket;
	private List<String> list = new ArrayList<String>();// 消息队列

	public TcpWrite(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	public synchronized void push(String string) {
		list.add(string);
		notify();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					socket.getOutputStream());
			while (true) {
				if (list.size() == 0) {
					this.wait();
				} else {
					byte[] bs = list.get(0).getBytes();
					bufferedOutputStream.write(bs);
					bufferedOutputStream.flush();
					// bufferedOutputStream.c
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
