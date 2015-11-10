package com.nitian.tcp;

import java.net.Socket;

public class SocketUser {

	private String username;// 用户惟一标识

	private String password;// 用户密码md5值

	private String tempSecret;// 临时密钥

	private Socket socket;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getTempSecret() {
		return tempSecret;
	}

	public void setTempSecret(String tempSecret) {
		this.tempSecret = tempSecret;
	}

}
