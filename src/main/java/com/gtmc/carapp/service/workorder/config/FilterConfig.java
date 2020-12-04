package com.gtmc.carapp.service.workorder.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan(basePackages="com.gtmc.carapp.service.workorder.filter")
public class FilterConfig {

}
