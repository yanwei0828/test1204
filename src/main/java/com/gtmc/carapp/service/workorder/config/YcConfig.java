package com.gtmc.carapp.service.workorder.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * yonyou cloud 相关配置
 * 
 * @author BENJAMIN
 *
 */
@Configuration
@ComponentScan(basePackages="com.yonyou.cloud")
@ServletComponentScan(basePackages="com.yonyou.cloud")
public class YcConfig {

}
