package xws;

public class App {

	private static App app = new App();
	
	public static void main(String[] args) {
		System.out.println(4%2==0?0:1);
	}

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
