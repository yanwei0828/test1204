package com.gtmc.carapp.service.workorder.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

public class CiphertextUtil {
	/**
	 * CiphertextUtil
	 *
	 * @author ysj
	 */

	public static final String MD5 = "MD5";
	public static final String SHA_1 = "SHA-1";
	public static final String SHA_256 = "SHA-256";
	private static final char[] CH_HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	/**
	 * 加密字符串
	 * 
	 * @param sourceStr
	 *            需要加密目标字符串
	 * @param algorithmsName
	 *            算法名称(如:MD5,SHA-1,SHA-256)
	 * @return
	 */
	public static String passAlgorithmsCiphering(String sourceStr, String algorithmsName) {
		String password = "";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(algorithmsName);
			// 使用指定byte[]更新摘要
			md.update(sourceStr.getBytes());
			// 完成计算，返回结果数组
			byte[] b = md.digest();
			password = byteArrayToHex(b);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}

	/**
	 * 将字节数组转为十六进制字符串
	 *
	 * @param bytes
	 * @return 返回16进制字符串
	 */
	private static String byteArrayToHex(byte[] bytes) {
		// 一个字节占8位，一个十六进制字符占4位；十六进制字符数组的长度为字节数组长度的两倍
		char[] chars = new char[bytes.length * 2];
		int index = 0;
		for (byte b : bytes) {
			// 取字节的高4位
			chars[index++] = CH_HEX[b >>> 4 & 0xf];
			// 取字节的低4位
			chars[index++] = CH_HEX[b & 0xf];
		}
		return new String(chars);
	}

	public static void main(String[] args) {
		String key = "88630de1459c14704317dc41b2899f18";//key替换
		String appId = "135";
		Integer count = 1;
		//System.out.println("\"key\":\"" + key +"\",");
		for (int i = 0; i < count; i++) {
			System.out.println((i+1)+"--start------------------------------");
			System.out.println("\"appId\":\"" + appId + "\",");
			Random random = new Random();
			int ranNum = random.nextInt(9000) + 1000;
			String nonce = String.valueOf(ranNum);
			System.out.println("\"nonce\":\"" + nonce +"\",");
			Date now = new Date();
			String timestamp = String.valueOf(now.getTime());
			System.out.println("\"timestamp\":\"" + timestamp + "\",");
			//生成签名
			String signature = passAlgorithmsCiphering(key + nonce + timestamp, SHA_1);
			System.out.println("\"signature\":\"" + signature + "\",");
			System.out.println((i+1)+"--end------------------------------");
		}
	}
}
