package com.nitian.socket.util.parse;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.nitian.socket.core.CoreType;

/**
 * 解析http请求头部,解析成Map<String,String>
 * 
 * @author 1036225283
 *
 */
public class UtilParseHttpRead {

	public static void parse(Map<String, String> map, String[] strings)
			throws UnsupportedEncodingException {
		String ip = UtilParseRead.getIp(strings);
		String port = UtilParseRead.getPort(strings);
		String url = UtilParseRead.getUrl(strings);
		String param = UtilParseRead.getParam(strings);
		String method = UtilParseRead.getMethod(strings);
		map.put(CoreType.ip.toString(), ip);
		map.put(CoreType.port.toString(), port);
		map.put(CoreType.url.toString(), url);
		map.put(CoreType.param.toString(), param);
		map.put(CoreType.method.toString(), method);
		map.put(CoreType.protocol.toString(), "HTTP");
	}

}
