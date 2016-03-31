package com.nitian.socket.util.parse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

/**
 * 解析http请求头部,解析成Map<String,String>
 * 
 * @author 1036225283
 *
 */
public class UtilParseHttpRead {

	protected LogManager log = LogManager.getInstance();

	private Map<String, String> map;
	private String[] strings;

	public UtilParseHttpRead(String request, Map<String, String> map) {
		// TODO Auto-generated constructor stub
		this.map = map;
		this.map.put("protocol", "HTTP");
		parse(request);
		getTypeAndUrlAndParam();
		getIpAndPort();

		log.info(LogType.debug, this, "----原生数据=" + request);
		log.info(LogType.debug, this, "----headers=" + strings.length);
		for (int i = 0; i < strings.length; i++) {
			log.info(LogType.debug, this, "----http>index>" + i + strings[i]);
		}

		log.info(LogType.debug, this, "----http>length-2>"
				+ strings[strings.length - 2]);
		log.info(LogType.debug, this, "----http>length-1>"
				+ strings[strings.length - 1]);
	}

	public void parse(String request) {
		strings = request.split("\r\n");
	}

	/**
	 * 解析get/post请求，解析传递参数
	 */
	public void getTypeAndUrlAndParam() {
		String[] type = strings[0].split(" ");
		if (type[0].equals("GET")) {
			map.put("type", "GET");
		} else if (type[0].equals("POST")) {
			map.put("type", "POST");
		}
		String[] urlAndParams = type[1].split("[?]");
		map.put("url", urlAndParams[0]);
		if (urlAndParams.length == 2) {// 检查是否有参数
			try {
				map.put("param", URLDecoder.decode(urlAndParams[1], "UTF8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int length = strings.length;
		if (strings[length - 2].equals("")) {
			String value = strings[length - 1];
			try {
				value = URLDecoder.decode(value, "UTF8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (map.get("param") == null) {
				map.put("param", value);
			} else {
				map.put("param", map.get("param") + "&" + value);
			}

		}
	}

	/**
	 * 解析ip和端口
	 */
	private void getIpAndPort() {
		String host = find("Host");
		String[] hosts = host.split(":");
		map.put("ip", hosts[1]);
		map.put("port", hosts[2]);
	}

	private String find(String key) {
		for (int i = 0; i < strings.length; i++) {
			if (strings[i].indexOf(key) == 0) {
				return strings[i];
			}
		}
		return null;
	}

}
