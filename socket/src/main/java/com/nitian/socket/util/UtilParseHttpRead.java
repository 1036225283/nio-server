package com.nitian.socket.util;


/**
 * 解析http请求头部
 * 
 * @author 1036225283
 *
 */
public class UtilParseHttpRead {
	
	private String request;
	private String[] strings;
	
	public UtilParseHttpRead(String request) {
		// TODO Auto-generated constructor stub
		this.request = request;
		parse();
	}

	public void parse() {
		 strings = request.split("\r\n");
		 System.out.println(request);
		for (int i = 0; i < strings.length; i++) {
			System.out.print(strings[i]);
		}
		
	}
	
	public String getType(){
		if(strings[0].contains("GET")){
			return "GET";
		}else if(strings[0].contains("POST")){
			return "POST";
		}else{
			return null;
		}
	}
	
	public String getUrl(){
		String[] line1 = strings[0].split(" ");
		return line1[1];
	}
	
	public String getIp(){
		return null;
	}
	
	public String getPort(){
		return null;
	}
	
	public String getParam(){
		return null;
	}
	
	
}
