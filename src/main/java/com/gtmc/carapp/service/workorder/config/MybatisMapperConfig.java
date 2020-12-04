package com.gtmc.carapp.service.workorder.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis 扫描 mapper
 * 
 * @author BENJAMIN
 *
 */
@Configuration
@MapperScan("com.gtmc.carapp.service.workorder.mapper")
public class MybatisMapperConfig {

}
