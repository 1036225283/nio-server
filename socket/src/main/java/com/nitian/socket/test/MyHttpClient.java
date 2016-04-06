package com.nitian.socket.test;

import java.io.*;
import java.net.*;

public class MyHttpClient {
	public static void main(String[] args) throws Exception {
		String result = post();
		// System.out.println(result);
		String[] strings = result.split("\r\n");
		for (int i = 0; i < strings.length; i++) {
			System.out.println(strings[i]);
		}
		System.out.println(result.length());
	}

	public static String getWebSocket() throws IOException {

		InetAddress inet = InetAddress.getByName("localhost");
		Socket socket = new Socket(inet.getHostAddress(), 88);
		OutputStream out = socket.getOutputStream();

		PrintWriter writer = new PrintWriter(out);
		writer.println("GET /examples/websocket/echoAnnotation HTTP/1.1");// home.html是关于百度的页面
		writer.println("Host: localhost:88");
		writer.println("User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64; rv:45.0) Gecko/20100101 Firefox/45.0");
		writer.println("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		writer.println("Accept-Language: en-us,zh-cn;q=0.5");
		writer.println("Accept-Encoding: gzip, deflate");
		writer.println("Sec-WebSocket-Version: 13");

		writer.println("Sec-WebSocket-Extensions: permessage-deflate");
		writer.println("Sec-WebSocket-Key: 0rG2wtoMWy9IWZKQ4VLrEA==");
		writer.println("Connection: keep-alive, Upgrade");

		writer.println("Pragma: no-cache");
		writer.println("Cache-Control: no-cache");
		writer.println("Upgrade: websocket");
		writer.println();
		writer.flush();

		InputStream in = socket.getInputStream();
		byte[] bs = new byte[1024 * 512];
		int length = in.read(bs);
		String result = new String(bs, 0, length);

		writer.close();
		socket.close();
		return result;
	}

	public static String get() throws IOException {

		InetAddress inet = InetAddress.getByName("localhost");
		Socket socket = new Socket(inet.getHostAddress(), 99);
		OutputStream out = socket.getOutputStream();

		PrintWriter writer = new PrintWriter(out);
		writer.println("GET /spring-mvc/test/test HTTP/1.1");// home.html是关于百度的页面
		writer.println("Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/msword, application/vnd.ms-excel, application/vnd.ms-powerpoint, */*");
		writer.println("Accept-Language: en-us,zh-cn;q=0.5");
		writer.println("Accept-Encoding: gzip, deflate");
		writer.println("Host: 168.1.0.99:8080");
		writer.println("User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
		writer.println("Connection: keep-alive");
		writer.println();
		writer.flush();

		InputStream in = socket.getInputStream();
		byte[] bs = new byte[1024 * 512];
		int length = in.read(bs);
		String result = new String(bs, 0, length);

		writer.close();
		socket.close();
		return result;
	}

	public static String post() throws IOException {

		InetAddress inet = InetAddress.getByName("localhost");
		Socket socket = new Socket(inet.getHostAddress(), 88);
		OutputStream out = socket.getOutputStream();

		PrintWriter writer = new PrintWriter(out);
		writer.println("POST /user/login HTTP/1.1");// home.html是关于百度的页面
		writer.println("Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/msword, application/vnd.ms-excel, application/vnd.ms-powerpoint, */*");
		writer.println("Accept-Language: en-us,zh-cn;q=0.5");
		writer.println("Accept-Encoding: gzip, deflate");
		writer.println("Host: 168.1.0.99:8080");
		writer.println("User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
		writer.println("Connection: keep-alive");
		writer.println();
		writer.flush();

		InputStream in = socket.getInputStream();
		byte[] bs = new byte[1024 * 512];
		int length = in.read(bs);
		String result = new String(bs, 0, length);

		writer.close();
		socket.close();
		return result;
	}
}