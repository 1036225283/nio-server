package test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Write extends Thread {

	public static void main(String[] args) throws IOException {
		new Write().start();
	}

	private Socket socket;
	private int index = 0;

	public Write() throws IOException {
		// TODO Auto-generated constructor stub
		InetAddress inet = InetAddress.getByName("localhost");
		socket = new Socket(inet.getHostAddress(), 8080);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				sleep(5000);
				index = index + 1;
				OutputStream out = socket.getOutputStream();

				PrintWriter writer = new PrintWriter(out);

				writer.println("this is test: " + index);
				writer.println();
				writer.flush();
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
