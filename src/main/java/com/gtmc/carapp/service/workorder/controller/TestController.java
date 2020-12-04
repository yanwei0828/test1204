package com.gtmc.carapp.service.workorder.controller;

import com.alibaba.fastjson.JSONObject;
import com.gtmc.carapp.service.workorder.dto.SubmitSignFileDto;
import com.gtmc.carapp.service.workorder.service.ConstructionOrderService;
import com.yonyou.cloud.common.annotation.YcApi;
import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.exception.BizException;
import com.yonyou.cloud.common.filter.UserLocal;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gtmc.carapp.service.workorder.common.CommonConstants;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value =  CommonConstants.BASE_PATH + "/test")
@Api(tags = "测试模块")
public class TestController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private final String pattern = "yyyy-MM-dd HH:mm:ss";

	@Autowired
	private Environment env;
	@Autowired
	private ConstructionOrderService constructionOrderService;

	@YcApi
	@ApiOperation(value = "测试电子施工单签名提交")
	@RequestMapping(value = "testSubmitSignFile", method = RequestMethod.POST)
	public RestResultResponse<?> testSubmitSignFile(@RequestBody SubmitSignFileDto submitSignFileDto)  {
		try {
			logger.info("电子施工单签名提交 start>>>>>>>" + JSONObject.toJSONString(submitSignFileDto));
			//参数非空校验
			if (StringUtils.isBlank(submitSignFileDto.getPhone())) {
				throw new BizException("手机号不能为空");
			}
			if (StringUtils.isBlank(submitSignFileDto.getSrvOrderNo())) {
				throw new BizException("工单号不能为空");
			}
			return new RestResultResponse<>().data(constructionOrderService.submitSignFile(submitSignFileDto)).success(true);
		} catch (Exception e) {
			logger.error("测试电子施工单签名提交", e);
			return new RestResultResponse<>().success(false).data("系统错误");
		}
	}

}
