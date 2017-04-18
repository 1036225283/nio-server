package com.nitian.socket.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Test {

	private ByteBuffer buffer = ByteBuffer.allocate(10);
	private IntBuffer intBuffer = IntBuffer.allocate(10);

	public void show() {
		System.out.println("position:" + buffer.position());
		System.out.println("limit:" + buffer.limit());
		System.out.println("capacity:" + buffer.capacity());
		System.out.println("show is end");
	}

	public void show(Buffer buffer) {
		System.out.println("position:" + buffer.position());
		System.out.println("limit:" + buffer.limit());
		System.out.println("capacity:" + buffer.capacity());
		System.out.println("show is end");
	}

	public static void main(String[] args) {

		Test test = new Test();
		test.show(test.intBuffer);
		test.intBuffer.put(12);
		test.show(test.intBuffer);
		test.intBuffer.put(13);
		test.show(test.intBuffer);
		System.out.println("[0]=" + test.intBuffer.get(0));
		System.out.println("[1]=" + test.intBuffer.get(1));
		test.show(test.intBuffer);
		test.intBuffer.flip();
		test.show(test.intBuffer);
		test.intBuffer.get(0);
		test.show(test.intBuffer);
	}
}
