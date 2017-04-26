package com.nitian.socket.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class UtilNIO {

	public static void read(SelectionKey key) {

		try {
			// 服务器可读取消息:得到事件发生的Socket通道
			SocketChannel channel = (SocketChannel) key.channel();
			// 创建读取的缓冲区
			ByteBuffer buffer = ByteBuffer.allocate(10);
			channel.read(buffer);
			byte[] data = buffer.array();
			String msg = new String(data).trim();
			ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
			channel.write(outBuffer);// 将消息回送给客户端
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
