package com.tinet.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
* 文件读取相关工具类
*<p>
* 文件名： ReadFileUtil.java
*<p>
* Copyright (c) 2006-2010 T&I Net Communication CO.,LTD.  All rights reserved.
* @author 娄雪
* @since 1.0
* @version 1.0
*/
public class ReadFileUtil {
	
	/**
	 * 读取文件或文件夹内容。
	 * @param fileName 文件或文件夹路径。
	 * @return 当参数为文件的路径时，返回文件内容的String串；当参数为文件夹路径时，返回文件夹下各文件的列表清单。
	 */
	public static String readFile(String fileName) { 
		String output = ""; 
		File file = new File(fileName); 
		if (file.exists()) {
			if (file.isFile()) {
				try { 
					InputStreamReader read = new InputStreamReader(new FileInputStream(fileName),"UTF-8"); 
					BufferedReader input = new BufferedReader(read); 
					StringBuffer buffer = new StringBuffer(); 
					String text; 
			
					while ((text = input.readLine()) != null) {
						buffer.append(text); 
					}
					output = buffer.toString(); 
				} catch (IOException ioException) { 
					System.err.println("File Error!"); 
				} 
			} else if (file.isDirectory()) { 
				String[] dir = file.list(); 
				output += "Directory contents:\n"; 
		
				for (int i = 0; i < dir.length; i++) { 
					output += dir[i] + "\n"; 
				} 
			} 
		} else { 
			System.err.println("The file does not exist!"); 
		} 
		return output; 
	} 
/**
 * 读取字符串中的ip
 * @param str
 * @return
 */
	public static String getIpFromString(String str) { 
		
		String pattern = "(\\d{1,3}\\.){3}\\d{1,3}";//从字符串获取ip的逻辑
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		String result = null;
		while (m.find()) {
		    result = m.group();
		    if (!"".equals(result)) {
			break;
		    }
		}
		boolean isOK = true;
		String[] array = result.split("\\.");//验证ip、是否合法的逻辑
		for (int i = 0; i < array.length; i++) {
		    int ip = Integer.parseInt(array[i]);
		    if (!(0 <= ip && ip <= 255)) {
			isOK = false;
			break;
		    }
		}
		if (isOK) {//如果ip合法
		    return result; 
		} else {
		    return "0.0.0.0";//如果不合法，返回默認ip
		}
		
	} 
	public static void main(String args[]) { 
		URL url = ReadFileUtil.class.getResource("/sql/ccic/cdr_ib.sql");
		String str = readFile( url.getPath()); 
		System.out.println(str);
	}
	
}
