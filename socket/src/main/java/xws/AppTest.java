package xws;

public class AppTest {

	public static App app = App.getInstance();

	public void test() {
		app.setUsername("xwss");
		TestThreadExtend testThread = new TestThreadExtend();
	}

	public static void main(String[] args) {

		AppTest appTest = new AppTest();
		appTest.test();

	}
}
