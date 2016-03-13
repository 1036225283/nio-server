package com.nitian.socket.tcp;

import java.net.Socket;
import java.util.Date;

public class SocketUser {

	private String username;// 用户惟一标识

	private String password;// 用户密码md5值

	private String tempSecret;// 临时密钥

	private Socket socket;

	private TcpRead tcpRead;

	private TcpWrite tcpWrite;

	private Date connectTime;// 建立连接的时间

	private int authTimes;// 认证次数

	private boolean authState = false;// 认证状态

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

	public TcpRead getTcpRead() {
		return tcpRead;
	}

	public void setTcpRead(TcpRead tcpRead) {
		this.tcpRead = tcpRead;
		this.tcpRead.setSocket(socket);
		this.tcpRead.setSocketUser(this);
	}

	public TcpWrite getTcpWrite() {
		return tcpWrite;
	}

	public void setTcpWrite(TcpWrite tcpWrite) {
		this.tcpWrite = tcpWrite;
		this.tcpWrite.setSocket(socket);
	}

	public Date getConnectTime() {
		return connectTime;
	}

	public void setConnectTime(Date connectTime) {
		this.connectTime = connectTime;
	}

	public int getAuthTimes() {
		return authTimes;
	}

	public void setAuthTimes(int authTimes) {
		this.authTimes = authTimes;
	}

	public boolean isAuthState() {
		return authState;
	}

	public void setAuthState(boolean authState) {
		this.authState = authState;
	}

}
