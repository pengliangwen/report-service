package com.goldcard.usmart.datasource;

//import org.springframework.core.io.ClassPathResource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
/**
 * ----------------------------------------------------------------
 * Copyright (C) 2017 金卡高科技股份有限公司
 * 版权所有。 
 * 
 * 文件名：AppDataSourceConfig.java
 * 文件功能描述：mysql 数据源
 * 
 * 创建标识 1990 2019年3月13日
 * 
 * 修改标识：
 * 修改描述：
 * ----------------------------------------------------------------
*/
@Configuration
@MapperScan(basePackages = {"com.goldcard.usmart.dao.mysql"},
        sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class AppDataSourceConfig {
    
	
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource adsDataSource() {
    	return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver").build();
    }
 
    @Bean(name = "mysqlTransactionManager")
    public DataSourceTransactionManager adsTransactionManager(@Qualifier("mysqlDataSource") DataSource adsDataSource) {
        return new DataSourceTransactionManager(adsDataSource);
    }
 
    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory adsSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource adsDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
       // sessionFactory.setConfigLocation(new ClassPathResource("classpath*:mybatis/mapper/*Mapper.xml"));
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:mybatis/mysql/*Mapper.xml"));
        sessionFactory.setDataSource(adsDataSource);
        return sessionFactory.getObject();
    }
    

}