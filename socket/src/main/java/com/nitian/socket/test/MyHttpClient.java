package com.nitian.socket.test;

import java.io.*;
import java.net.*;

public class MyHttpClient {
	public static void main(String[] args) throws Exception {
		String result = get();
		System.out.println(result);
		String[] strings = result.split("\r\n\r\n");
		System.out.println(strings[0].length());
		System.out.println(strings[1].length());
		System.out.println(result.length());
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
}