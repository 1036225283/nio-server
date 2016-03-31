package xws;

public class TestThread extends Thread {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		handl();
	}

	public void handl() {
		System.out.println("this is handl");
	}
}
