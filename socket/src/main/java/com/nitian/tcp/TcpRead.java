package com.nitian.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

import com.nitian.util.json.UtilJson;
import com.nitian.util.socket.UtilSocket;
import com.nitian.util.user.UtilAuthUser;

public class TcpRead extends Thread {

	private Socket socket;

	private SocketUser socketUser;

	private byte[] bs = new byte[1024 * 8 + 4];

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			InputStream inputStream = socket.getInputStream();
			while (true) {
				String value = UtilSocket.readString(inputStream, bs);
				Map<String, Object> map = (Map<String, Object>) UtilJson
						.stringToObject(value, Map.class);
				if (socketUser.isAuthState()) {

				} else if (socketUser.getAuthTimes() > 3) {

				} else {
					if (UtilAuthUser.authUserTest()) {
						socketUser.setAuthState(true);
					} else {
						socketUser.setAuthTimes(socketUser.getAuthTimes() + 1);
					}
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public SocketUser getSocketUser() {
		return socketUser;
	}

	public void setSocketUser(SocketUser socketUser) {
		this.socketUser = socketUser;
	}
}
