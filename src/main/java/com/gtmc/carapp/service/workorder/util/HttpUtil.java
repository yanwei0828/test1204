package com.gtmc.carapp.service.workorder.util;

import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


public class HttpUtil {
	private static Logger logger = Logger.getLogger(HttpUtil.class);

	/**
	 * post方式请求
	 * @param url			请求地址
	 * @param jsonBody	参数内容格式为: {"name":"你好"}
	 * @return
	 */
	public static String post(String url, String jsonBody) {
		String result ="";
		// 创建默认的httpClient实例
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000*1000).setConnectTimeout(100*1000).build();
		httpPost.setConfig(requestConfig);
		// 创建参数队列
		try {
			StringEntity input = new StringEntity(jsonBody, "UTF-8");
			input.setContentType("application/json;charset=utf-8");
			httpPost.setEntity(input);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			httpPost.abort();
			logger.error("异常" + e.getMessage(), e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("异常" + e.getMessage(), e);
			}
		}
		return result;
	}

	/**
	 * post方式请求
	 * @param url			请求地址
	 * @param jsonBody	参数内容格式为: {"name":"你好"}
	 * @param headers	请求头
	 * @return
	 */
	public static String postWithHeaders(String url, String jsonBody, Map<String,String> headers) {
		String result ="";
		// 创建默认的httpClient实例
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httpPost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000*1000).setConnectTimeout(100*1000).build();
		httpPost.setConfig(requestConfig);
		if(headers != null && headers.size() > 0){
			for (String k : headers.keySet()){
				httpPost.addHeader(k,headers.get(k));
			}
		}

		// 创建参数队列
		try {
			StringEntity input = new StringEntity(jsonBody, "UTF-8");
			input.setContentType("application/json;charset=utf-8");
			httpPost.setEntity(input);
			logger.info("postWithHeaders>>>>" + JSONObject.toJSONString(httpPost));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			httpPost.abort();
			logger.error("异常" + e.getMessage(), e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				logger.error("异常" + e.getMessage(), e);
			}
		}
		return result;
	}

}