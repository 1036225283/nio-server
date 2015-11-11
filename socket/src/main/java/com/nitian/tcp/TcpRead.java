package com.nitian.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Map;

import com.nitian.util.http.UtilHttp;
import com.nitian.util.java.UtilByte;
import com.nitian.util.json.JsonStringToObject;
import com.nitian.util.json.UtilJson;

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
				byte[] length = new byte[4];
				inputStream.read(length, 0, 4);
				int size = UtilByte.bytesToInt(length);
				inputStream.read(bs, 0, size);
				String value = new String(bs, 0, size);
				Map<String, Object> map = (Map<String, Object>) UtilJson
						.stringToObject(value, Map.class);
				if (socketUser.isAuthState()) {

				} else {
					String param = "username=" + map.get("username")
							+ "&password=" + map.get("password");
					String result = UtilHttp.noSessionPost(
							"http://localhost:8080/franchisee-web/member/test",
							param);
					JsonStringToObject jsonStringToObject = new JsonStringToObject();
					jsonStringToObject.goString(result);
					String aa = jsonStringToObject.get("root.result");
					if (aa.equals("true")) {
						socketUser.setAuthState(true);
					} else {

					}
					socketUser.setAuthTimes(socketUser.getAuthTimes() + 1);
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
