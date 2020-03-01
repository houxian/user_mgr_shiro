package com.qiku.usermgr.common.utils;

/**
 * 字符串工具类
 * @author houxianyong
 * @date  2020-02-21
 */
public class StringUtils {

	/**
	 * 判空操作
	 * @param value
	 * @return
	 */
	public static boolean isBlank(String value) {
		return value == null || "".equals(value) || "null".equals(value) || "undefined".equals(value);
	}

}
