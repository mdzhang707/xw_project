package com.manage.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {
	public static String encrypt(String origStr) {
		String str = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");// sha
			byte[] buf = md.digest(origStr.getBytes());
			BASE64Encoder encode = new BASE64Encoder();
			str = encode.encode(buf);
		} catch (Exception e) {
			//throw e;
		}
		return str;
	}
}
