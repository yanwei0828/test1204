package com.gtmc.carapp.service.workorder.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;

public class Test {
	public static void main(String[] args) {
		final Base64.Decoder decoder = Base64.getDecoder();
		final Base64.Encoder encoder = Base64.getEncoder();
		//final String text = "字串文字";
		final String text = "{\"token\":\"99bae7f5-f569-4219-8817-8000feaeb7e7\"}";
		byte[] textByte;
		try {
			textByte = text.getBytes("UTF-8");
			
			//编码
			final String encodedText = encoder.encodeToString(textByte);
			System.out.println(encodedText);
			//解码
			System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
			//UrlEncode
			System.out.println(URLEncoder.encode(text));
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//final Base64.Decoder decoder = Base64.getDecoder();
		//final Base64.Encoder encoder = Base64.getEncoder();
		//final String text = "字串文字";
		//final byte[] textByte = text.getBytes("UTF-8");
		//编码
		//final String encodedText = encoder.encodeToString(textByte);
		//System.out.println(encodedText);
		//解码
		//System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
	}

}
