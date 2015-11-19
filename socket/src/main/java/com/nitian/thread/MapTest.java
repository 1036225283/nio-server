package com.nitian.thread;

import com.nitian.util.http.UtilHttp;
import com.nitian.util.json.JsonStringToObject;

public class MapTest {

	public static void main(String[] args) {
		String param = "username=username"+ "&password=password";
		String result = UtilHttp.noSessionPost(
				"http://localhost:8080/franchisee-web/member/test",
				param);
		JsonStringToObject jsonStringToObject = new JsonStringToObject();
		jsonStringToObject.goString(result);
		System.out.println(jsonStringToObject.get("root.result"));
	}
}
