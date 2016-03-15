package com.nitian.socket.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import com.nitian.socket.ApplicationContext;


/**
 * 解析http请求头部
 * 
 * @author 1036225283
 *
 */
public class UtilParseHttpRead {
	
	private ApplicationContext applicationContext = ApplicationContext
			.getInstance();
	
	private Map<String, Object> map = applicationContext.getPoolMap().lend();
	private String request;
	private String[] strings;
	
	private String url;
	private String ip;
	private String port;
	
	public UtilParseHttpRead(String request) {
		// TODO Auto-generated constructor stub
		this.request = request;
		parse();
		getTypeAndUrlAndParam();
		getIpAndPort();
		
	}

	public void parse() {
		 strings = request.split("\r\n");
		 System.out.println(request);
		 System.out.println("headers.size : "+strings.length);
		for (int i = 0; i < strings.length; i++) {
			System.out.println(strings[i]);
		}
	}
	
	public void getTypeAndUrlAndParam(){
		String get = find("GET");
		String post = find("POST");
		if(get!=null){
			map.put("type", "GET");
			String urlAndParam = get.split(" ")[1];
			String[]urlAndParams = urlAndParam.split("[?]");
			map.put("url", urlAndParams[0]);
			if(urlAndParams.length==2){
				try {
					map.put("param", URLDecoder.decode(urlAndParams[1], "UTF8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return;
		}
		
		if(post!=null){
			map.put("type", "POST");
		}
	}
	
	/**
	 * 解析ip和端口
	 */
	private void getIpAndPort(){
		String host = find("Host");
		String[] hosts = host.split(":");
		map.put("ip", hosts[1]);
		map.put("port", hosts[2]);
	}
	
	private String getParam(){
		return null;
	}
	
	private String find(String key){
		for (int i = 0; i < strings.length; i++) {
			if(strings[i].indexOf(key)==0){
				return strings[i];
			}
		}
		return null;
	}
	
	public Map<String, Object> getMap(){
		return map;
	}
	
}
