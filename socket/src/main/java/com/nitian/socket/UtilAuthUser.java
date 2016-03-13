package com.nitian.socket;

import java.util.Map;

import com.nitian.util.http.UtilHttp;
import com.nitian.util.json.JsonStringToObject;

/**
 * 验证用户
 * 
 * @author 1036225283
 *
 */
public class UtilAuthUser {

	/**
	 * 获取参数，验证用户账户和密码
	 * 
	 * @param map
	 * @return
	 */
	public static boolean authUser(Map<String, Object> map) {
		String param = "username=" + map.get("username") + "&password="
				+ map.get("password");
		String result = UtilHttp.noSessionPost(
				"http://localhost:8080/franchisee-web/member/test", param);
		JsonStringToObject jsonStringToObject = new JsonStringToObject();
		jsonStringToObject.goString(result);
		String aa = jsonStringToObject.get("root.result");
		if (aa.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 测试用，模拟用户验证
	 * 
	 * @return
	 */
	public static boolean authUserTest() {
		int index = (int) (Math.random() * 10);
		System.out.println(index);
		if (index > 5) {
			return true;
		} else {
			return false;
		}
	}

}
