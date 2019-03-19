package com.goldcard.usmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;




/**
 * ----------------------------------------------------------------
 * Copyright (C) 2017 金卡高科技股份有限公司
 * 版权所有。 
 * 
 * 文件名：SpringBootBitTableServer.java
 * 文件功能描述：TODO
 * 
 * 创建标识 1990 2019年3月13日
 * 
 * 修改标识：
 * 修改描述：
 * ----------------------------------------------------------------
*/
@SpringBootApplication
@EnableConfigurationProperties
public class SpringBootBigTableServer {

	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBootBigTableServer.class);
		app.run(args);
	}
	
}
