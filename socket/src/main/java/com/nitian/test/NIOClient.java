package com.nitian.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {
	// 通道管理器
	private Selector selector;
	private SocketChannel channel;

	/**
	 * 获得一个Socket通道，并对该通道做一些初始化的工作
	 * 
	 * @param ip
	 *            连接的服务器的ip
	 * @param port
	 *            连接的服务器的端口号
	 * @throws IOException
	 */
	public void listen(String ip, int port) {
		try {
			channel = SocketChannel.open();
			channel.configureBlocking(false);
			this.selector = Selector.open();

			// 用channel.finishConnect();才能完成连接
			channel.connect(new InetSocketAddress(ip, port));
			channel.register(selector, SelectionKey.OP_CONNECT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
	 * 
	 * @throws IOException
	 */
	public void start() {
		// 轮询访问selector
		while (true) {
			try {
				selector.select();
				Iterator<SelectionKey> ite = this.selector.selectedKeys()
						.iterator();
				while (ite.hasNext()) {
					SelectionKey key = (SelectionKey) ite.next();
					ite.remove();
					if (key.isConnectable()) {
						SocketChannel channel = (SocketChannel) key.channel();
						if (channel.isConnectionPending()) {
							channel.finishConnect();
						}
						channel.configureBlocking(false);

						// 在这里可以给服务端发送信息哦
						channel.write(ByteBuffer.wrap(new String("向服务端发送了一条信息")
								.getBytes()));
						// 在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
						channel.register(this.selector, SelectionKey.OP_READ);

						// 获得了可读的事件
					} else if (key.isReadable()) {
						read(key);
					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 处理读取服务端发来的信息 的事件
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException {
		// 和服务端的read方法一样
	}

	/**
	 * 启动客户端测试
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		NIOClient client = new NIOClient();
		client.listen("localhost", 8080);
		client.start();
	}

}