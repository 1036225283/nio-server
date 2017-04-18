package com.nitian.socket.thread;

public class Hello extends Thread {

	public volatile static int duck = 30;

	public synchronized void show() {
		duck = duck - 1;
		System.out.println(Thread.currentThread().getId() + " " + duck);
		if (duck == 80 || duck == 70) {
			try {
				this.wait();
				System.out.println("i am sorry");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("this is wait");
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (duck > 0) {
			show();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void toNotify() {
		this.notify();
	}

	public static void main(String[] args) {
		Hello hello = new Hello();
		hello.start();
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hello.toNotify();
		hello.toNotify();
		hello.toNotify();
		System.out.println("end");
		// new Hello().start();
		// new Hello().start();
	}
}
