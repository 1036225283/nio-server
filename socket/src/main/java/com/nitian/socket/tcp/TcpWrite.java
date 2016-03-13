package com.nitian.socket.tcp;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.nitian.util.java.UtilByte;

/**
 * 多线程socket写数据
 * 
 * @author 1036225283
 *
 */
public class TcpWrite extends Thread {

	private Socket socket;
	private List<String> list = new ArrayList<String>();// 消息队列
	private byte[] bs = new byte[1024 * 8 + 4];

	public synchronized void push(String string) {
		list.add(string);
		System.out.println("list size:" + list.size());
		notify();
	}

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		try {

			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					socket.getOutputStream());
			while (true) {
				if (list.size() == 0) {
					System.out.println("list size" + list.size());
					wait();
				} else {
					byte[] data = list.get(0).getBytes();
					byte[] length = UtilByte.intToBytes(data.length);
					UtilByte.copy(bs, length, 0);
					UtilByte.copy(bs, data, 4);
					bufferedOutputStream.write(bs, 0, data.length + 4);
					bufferedOutputStream.flush();
					list.remove(0);
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
