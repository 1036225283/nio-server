package xws;

public class App {

	private static App app = new App();

	public static App getInstance() {
		return app;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String username = "";
}
