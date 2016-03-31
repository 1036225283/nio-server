package xws;

public class TestThreadExtend extends TestThread {

	private App app = App.getInstance();

	public static void main(String[] args) {
		TestThreadExtend testThread = new TestThreadExtend();
	}

	public TestThreadExtend() {
		// TODO Auto-generated constructor stub
		start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("this is run");
		System.out.println(app.getUsername() + "--");
	}
}
