package com.nitian.thread;

public class User {
	
	public static void main(String[] args) {
		User user = new User();
		user.setA(user.getA()+10);
		System.out.println(user.getA());
		
	}

	private int a;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
}
