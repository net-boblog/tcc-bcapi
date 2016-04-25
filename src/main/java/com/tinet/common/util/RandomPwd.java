package com.tinet.common.util;

import java.util.Random;

public class RandomPwd {
	/** * 产生随机字符串	* */		
	private static Random randGen = null;		
	private static char[] numbersAndLetters = null;	
	// length 产生随机字符串位数
	public static final String randomString(int length) {
		if (length < 1) {
			return null;		    
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		}
		char [] randBuffer = new char[length];
		for (int i=0; i<length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(numbersAndLetters.length)];
		}		         
		return new String(randBuffer);
	}
}
