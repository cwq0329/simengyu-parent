package com.cwq.core.tools;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * 加密工具类
 * 
 * @author Administrator
 *
 */
public class Encryption {

	/**
	 * 对字符串进行加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encrypt(String str) {
		// 如有需要还可以对密码进行加盐
		// str = "yfy67v4a7vghfe" + str + "eruweyr786f";
		String pwd = null;
		// 使用md5加密方式
		try {
			MessageDigest instance = MessageDigest.getInstance("md5");
			// 开始加密,参数需要将字符串转字节数组,返回的也是字节数组
			byte[] digest = instance.digest(str.getBytes());
			// 将字节数组转十六进制字符串
			char[] encodeHex = Hex.encodeHex(digest);
			pwd = new String(encodeHex);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return pwd;

	}

	// public static void main(String[] args) {
	// System.out.println(Encryption.encrypt("123456"));
	// }
}
