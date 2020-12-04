package com.gtmc.carapp.service.workorder.converter;

import org.springframework.core.env.Environment;

import com.xiaoleilu.hutool.system.SystemUtil;
import com.yonyou.cloud.common.reflection.SpringUtil;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class InstanceIdConverter extends ClassicConverter {

	public static String instanceId;

	@Override
	public String convert(ILoggingEvent event) {
		if (instanceId == null) {
			Environment environment = (Environment) SpringUtil.getBean("environment");
			instanceId = SystemUtil.getHostInfo().getName() + ":" + environment.getProperty("spring.application.name")
					+ ":" + environment.getProperty("server.port");
		}
		return instanceId;
	}

}