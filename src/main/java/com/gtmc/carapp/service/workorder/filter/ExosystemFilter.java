package com.gtmc.carapp.service.workorder.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.gtmc.carapp.service.workorder.dto.base.BaseExoValidateDto;
import com.gtmc.carapp.service.workorder.entity.TtApiAccessAllot;
import com.gtmc.carapp.service.workorder.entity.TtApiAccessHistory;
import com.gtmc.carapp.service.workorder.service.AccessAllotService;
import com.gtmc.carapp.service.workorder.service.AccessHistoryService;
import com.gtmc.carapp.service.workorder.util.CiphertextUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.beans.ResultBean;

@WebFilter(urlPatterns = "/workorder/exo/*", filterName = "ExosystemFilter")
public class ExosystemFilter implements Filter {

	/**
	 * 日志 
	 */
	private static Logger logger = Logger.getLogger(ExosystemFilter.class);

	/**
	 * 访问分配服务
	 */
	@Autowired
	private AccessAllotService accessAllotService;

	/**
	 * 访问历史服务
	 */
	@Autowired
	private AccessHistoryService accessHistoryService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("ExosystemFilter init!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		@SuppressWarnings("unchecked")
		RestResultResponse<Integer> result = new RestResultResponse<Integer>().success(false);
		result.setResultCode(ResultBean.VALID_FAILD);
		response.setContentType("application/json;charset=UTF-8");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		try {
			// 接口只支持post请求
			if (RequestMethod.POST.name().equalsIgnoreCase(httpServletRequest.getMethod())) {
				ServletRequest requestWrapper = new PostBodyRequestWrapper(httpServletRequest);
				String body = HttpHelper.getBodyString(requestWrapper);
				if (StrUtil.isBlank(body)) {
					result.setErrMsg("RequestBody不能为空");
					response.getOutputStream().write(JSON.toJSONString(result).getBytes(Charset.forName("UTF-8")));
					return;
				} else {
					BaseExoValidateDto validate = JSON.parseObject(body, BaseExoValidateDto.class);
					logger.info("签名认证参数：" + JSON.toJSONString(validate));
					if (StrUtil.isBlank(validate.getAppId()) || StrUtil.isBlank(validate.getNonce())
							|| StrUtil.isBlank(validate.getTimestamp()) || StrUtil.isBlank(validate.getSignature())) {
						result.setErrMsg("认证失败，必须参数不能为空");
						response.getOutputStream().write(JSON.toJSONString(result).getBytes(Charset.forName("UTF-8")));
						return;
					} else {

						// 根据appId获取appKey
						TtApiAccessAllot allot = new TtApiAccessAllot();
						allot.setAppId(Integer.valueOf(validate.getAppId()));
						allot = accessAllotService.selectOne(allot);
						// 不存在
						if (allot == null) {
							result.setErrMsg("认证失败，appId不合法");
							response.getOutputStream()
									.write(JSON.toJSONString(result).getBytes(Charset.forName("UTF-8")));
							return;
						}
						// 生成签名
						String sign = allot.getAppKey() + validate.getNonce() + validate.getTimestamp();
						logger.info("加密前签名：" + sign);
						sign = CiphertextUtil.passAlgorithmsCiphering(sign, CiphertextUtil.SHA_1);
						logger.info("加密后签名：" + sign);
						if (validate.getSignature().equals(sign)) {
							// 判断签名是否重复调用
							TtApiAccessHistory history = new TtApiAccessHistory();
							history.setAppId(Integer.valueOf(validate.getAppId()));
							history.setNonce(validate.getNonce());
							history.setTimestamp(validate.getTimestamp());
							history.setSignature(validate.getSignature());
							long cnt = accessHistoryService.selectCount(history);
							if (cnt > 0) {
								result.setErrMsg("认证失败，签名重复使用");
								response.getOutputStream()
										.write(JSON.toJSONString(result).getBytes(Charset.forName("UTF-8")));
								return;
							} else {
								// 保存认证签名
								history.setCreateBy(validate.getAppId());
								history.setCreateDate(new Date());
								accessHistoryService.insertSelective(history);
							}
						} else {
							result.setErrMsg("认证失败，签名加密错误");
							response.getOutputStream()
									.write(JSON.toJSONString(result).getBytes(Charset.forName("UTF-8")));
							return;
						}
					}
					chain.doFilter(requestWrapper, response);
				}
			} else {
				result.setErrMsg("API只支持POST请求");
				response.getOutputStream().write(JSON.toJSONString(result).getBytes(Charset.forName("UTF-8")));
				return;
			}
		} catch (Exception e) {
			logger.error("API认证失败：", e);
			result.setErrMsg("认证失败，" + e.getMessage());
			response.getOutputStream().write(JSON.toJSONString(result).getBytes(Charset.forName("UTF-8")));
			return;
		}
	}

	@Override
	public void destroy() {

	}
	
	public static void main(String[] args) {
		Date date =  new Date();
		// 生成签名
		String sign = "db155be5dd9346a7736563d3f62fefbb" + "6666" + date.getTime();
		logger.info("加密前签名：" + sign);
		sign = CiphertextUtil.passAlgorithmsCiphering(sign, CiphertextUtil.SHA_1);
		logger.info("加密后签名：" + sign);
	}

}
