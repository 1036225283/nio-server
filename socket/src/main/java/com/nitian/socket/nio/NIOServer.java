package com.nitian.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServer {

	// private Selector selector;
	private ServerSocketChannel serverSocketChannel = null;

	public NIOServer listen(int port) {
		try {
			// selector = Selector.open();// 创建Selector实例
			serverSocketChannel = ServerSocketChannel.open();
			// serverSocketChannel.configureBlocking(false);
			// serverSocketChannel.socket().setReuseAddress(true);
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
			// serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}

	public void start() {
		try {
			while (true) {
				SocketChannel socketChannel = serverSocketChannel.accept();
				System.out.println("this is accept" + socketChannel);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 处理读取客户端发来的信息 的事件
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		// 创建读取的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data).trim();
		System.out.println("服务端收到信息：" + msg);
		ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
		channel.write(outBuffer);// 将消息回送给客户端
	}

	public static void main(String[] args) {
		NIOServer server = new NIOServer();
		server.listen(99).start();
	}

}
