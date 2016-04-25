package com.tinet.common.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 针对数组的工具类。继承自org.apache.commons.lang.ArrayUtils。
 *<p>
 * 文件名： ArrayUtil.java
 *<p>
 * Copyright (c) 2006-2010 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 周营昭
 * @since 1.0
 * @version 1.0
 */

public final class ArrayUtil extends ArrayUtils{

	public static final String EMPTY_STRING_ARRAY[] = new String[0];

	public ArrayUtil() {
	}

	/**
	 * 将一个字符串按照指定分隔符转换成字符串数组。
	 * 
	 * @param value 原始字符串。
	 * @param delim 指定分隔符。
	 * @return 分割后的字符串数组。
	 */
	public static String[] toStringArray(String value, String delim) {
		if (value != null)
			return StringUtil.split(value, delim);
		else
			return EMPTY_STRING_ARRAY;
	}
	
	/**
	 * 将一个任意类型数组转换成字符串数组。
	 * 
	 * @param array 原始的数组。
	 * @return 转换后的字符串数组。
	 */
	public static String[] toString(Object[] array) {
		String[] result = null;
		if (isEmpty(array)) {
			return result;
		} else {
			int i = 0;
			result = new String[array.length];
			for (Object obj : array) {
				result[i] = String.valueOf(obj);
				i++;
			}
			return result;
		}
	}
	
	/**
	 * 将一个数组转换成ArrayList。
	 * 
	 * @param array 被转换的数组。
	 * @return ArrayList
	 */
	public static List toList(Object array) {
		if (array == null) {
			return new ArrayList();
		}
		
		if (array instanceof Object[]) {
			return Arrays.asList((Object[]) array);
		}
		
		int size = Array.getLength(array);
		ArrayList list = new ArrayList();
		for (int i = 0; i < size; i++) {
			list.add(Array.get(array, i));
		}
		
		return list;
	}

	/**
	 * 判断一个字符串数组是否是空字符串数组。如果每一个元素都是空字符串，则也被认为是空字符串数组。
	 * 
	 * @param array 被判断的字符串。
	 * @return true or false
	 */
	public static boolean isEmptyStringArray(String[] array) {
		if (array == null || array.length == 0) {
			return true;
		}
		
		for (String item : array) {
			if (!StringUtils.isEmpty(item)) {
				return false;
			}
		}
		
		return true;
	}
}
