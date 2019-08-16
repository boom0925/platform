package com.shiro.utils;

import com.alibaba.fastjson.JSONObject;
import com.shiro.utils.constants.Constants;
import com.shiro.utils.constants.ErrorEnum;


public class CommonUtil {

	/**
	 * 返回一个info为空对象的成功消息的json
	 */
	public static JSONObject successJson() {
		return successJson(new JSONObject());
	}

	/**
	 * 返回一个返回码为100的json
	 */
	public static JSONObject successJson(Object info) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", Constants.SUCCESS_CODE);
		resultJson.put("msg", Constants.SUCCESS_MSG);
		resultJson.put("info", info);
		return resultJson;
	}

	/**
	 * 返回错误信息JSON
	 */
	public static JSONObject errorJson(ErrorEnum errorEnum) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", errorEnum.getErrorCode());
		resultJson.put("msg", errorEnum.getErrorMsg());
		resultJson.put("info", new JSONObject());
		return resultJson;
	}
}
