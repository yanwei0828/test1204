package com.gtmc.carapp.service.workorder.controller;

import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtmc.carapp.service.workorder.common.CommonConstants;

@RestController
@RequestMapping(value =  CommonConstants.BASE_PATH + "/app")
@Api(tags = "app请求模块")
public class AppController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	



}
