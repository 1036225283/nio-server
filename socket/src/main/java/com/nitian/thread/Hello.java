package com.nitian.thread;

public class Hello extends Thread {

	public volatile static int duck = 100;

	public  void show() {
		duck = duck - 1;
		System.out.println(duck);
//		this.s
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (duck > 0) {
			show();
		}
	}

	public static void main(String[] args) {
		new Hello().start();
		new Hello().start();
		new Hello().start();
	}
}
