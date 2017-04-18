package com.nitian.socket.util.list;

import com.nitian.socket.util.thread.ThreadWebSocket;

/**
 * webSocket有限集合
 * 
 * @author 1036225283
 *
 */
public class UtilListWebSocketThread extends UtilList<ThreadWebSocket> {

	public UtilListWebSocketThread() {
		// TODO Auto-generated constructor stub
		setMax(10000);
	}

}
