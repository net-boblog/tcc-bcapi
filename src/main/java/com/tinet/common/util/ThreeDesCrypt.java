package com.tinet.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


import org.apache.commons.codec.binary.*;

/**
 * 字符串 DESede(三重des算法)加密
 */

public class ThreeDesCrypt {

	/**
	 * 加密采用的算法（DES,DESede,Blowfish等）
	 */
	private static final String ALGORITHM = "DESede";

	/**
	 * 对字符串src加密
	 * 
	 * @param key 密钥
	 * @param src 字符串
	 * @return 加密后的字符串
	 * @throws UnsupportedEncodingException 编码异常
	 */
	public static String encrypt(String key, String src) throws UnsupportedEncodingException {
		byte[] keybyte = new byte[24];
		if (key == null || src == null) {
			return null;
		}

		key = new String(key.getBytes("UTF8"), "UTF8");
		for (int i = 0; i < 24; i++) {
			if (i < key.length()) {
				keybyte[i] = (byte) key.charAt(i);
			} else {
				keybyte[i] = '0';
			}
		}
		String miSrc = Base64.encodeBase64String(encryptMode(keybyte, src.getBytes("UTF8")));
		keybyte = null;
		return miSrc;
	}

	/**
	 * 对字符串src解密
	 * 
	 * @param key 密钥
	 * @param src 字符串
	 * @return 解密后的字符串
	 * @throws IOException IO异常
	 */
	public static String decrypt(String key, String src) throws IOException {
		byte[] keybyte = new byte[24];
		if (key == null || src == null) {
			return null;
		}

		for (int i = 0; i < 24; i++) {
			if (i < key.length()) {
				keybyte[i] = (byte) key.charAt(i);
			} else {
				keybyte[i] = '0';
			}
		}
		byte[] decSrc = Base64.decodeBase64(src);
		return new String(decryptMode(keybyte, decSrc));
	}

	/**
	 * 加密数据转换
	 * 
	 * @param keybyte 加密密钥，长度为24字节
	 * @param src 被加密的数据缓冲区（源）
	 * @return 加密后的数据
	 */
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, ALGORITHM);

			// 加密
			Cipher c1 = Cipher.getInstance(ALGORITHM);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密数据转换
	 * 
	 * @param keybyte 加密密钥，长度为24字节
	 * @param src 加密后的数据缓冲区（源）
	 * @return 解密后的数据
	 */
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, ALGORITHM);

			// 解密
			Cipher c1 = Cipher.getInstance(ALGORITHM);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	/**
	 * 转换成十六进制字符串
	 * 
	 * @param b 字节
	 * @return 十六进制字符串
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";

		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}

			if (n < b.length - 1) {
				hs = hs + ":";
			}

		}
		return hs.toUpperCase();
	}

	/**
	 * 十六进制字符串转换成字节
	 * 
	 * @param hex 十六进制字符串
	 * @return 字节
	 */
	public static byte[] hex2byte(String hex) {
		String[] stmp = hex.split(":");
		byte[] b = new byte[stmp.length];
		for (int n = 0; n < stmp.length; n++) {
			String t = stmp[n];
			b[n] = Integer.decode("0x" + t).byteValue();

		}
		return b;
	}

	/**
	 * 根据参数生成KEY
	 * 
	 * @param strKey
	 */
	public Key getKey(String strKey) {
		Key key = null;
		try {
			KeyGenerator _generator = KeyGenerator.getInstance(ALGORITHM);
			_generator.init(new SecureRandom(strKey.getBytes("UTF8")));
			key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}


	public static byte[] geneIV() {
		byte[] iv = null;
		try {
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			KeyGenerator generator = KeyGenerator.getInstance("DESede");
			SecureRandom random = new SecureRandom();
			generator.init(168, random);
			SecretKey deskey = generator.generateKey();
			cipher.init(Cipher.ENCRYPT_MODE, deskey);
			iv = cipher.getIV();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iv;
	}

	public static byte[] encodeDESedeCBCNoPadding(byte[] source, String key, byte[] iv) {
		try {
			String ALGORITHM = "DESede/CBC/NoPadding";
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			KeySpec spec = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			SecretKey deskey = factory.generateSecret(spec);
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ivspec);
			byte[] ciphertext = cipher.doFinal(source);
			return ciphertext;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] encodeDESedeCBCPKCS5Padding(byte[] source, String key, byte[] iv) {
		try {
			String ALGORITHM = "DESede/CBC/PKCS5Padding";
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			KeySpec spec = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			SecretKey deskey = factory.generateSecret(spec);
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			cipher.init(Cipher.ENCRYPT_MODE, deskey, ivspec);
			byte[] ciphertext = cipher.doFinal(source);
			return ciphertext;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] decodeDESedeCBCNoPadding(byte[] source, String key, byte[] iv) {
		try {
			String ALGORITHM = "DESede/CBC/NoPadding";
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			KeySpec spec = new DESedeKeySpec(key.getBytes());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			SecretKey deskey = factory.generateSecret(spec);
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, deskey, ivspec);

			byte[] ciphertext = cipher.doFinal(source);
			return ciphertext;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) throws IOException { // 添加新安全算法,如果用JCE就要把它添加进去
		String key = "my_pass_word_key";//密钥
		
		String szSrc = "hello,world!你好，世界！";
	System.out.println("加密前:"+szSrc);	
		
		String encoded = encrypt(key, szSrc);
	System.out.println("加密后:"+encoded);
	
		String decoded=  decrypt(key,encoded);	
	System.out.println("解密后:"+decoded);
		
	
	}
	
}