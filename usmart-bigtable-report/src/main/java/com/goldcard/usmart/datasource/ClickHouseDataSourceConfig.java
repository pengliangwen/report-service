package com.goldcard.usmart.datasource;

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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * ----------------------------------------------------------------
 * Copyright (C) 2017 金卡高科技股份有限公司
 * 版权所有。 
 * 
 * 文件名：ClickHouseDataSourceConfig.java
 * 文件功能描述：clickHouse数据源
 * 
 * 创建标识 1990 2019年3月13日
 * 
 * 修改标识：
 * 修改描述：
 * ----------------------------------------------------------------
*/
@Configuration
@MapperScan(basePackages = {"com.goldcard.usmart.dao.ch"},
        sqlSessionFactoryRef = "chSqlSessionFactory")
public class ClickHouseDataSourceConfig {
    //private static final Logger log = LoggerFactory.getLogger(ClickHouseDataSourceConfig.class);
    
	
    @Bean(name = "chDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ch")
    public DataSource chDataSource() {
        return DataSourceBuilder.create().driverClassName("ru.yandex.clickhouse.ClickHouseDriver").build();
    }
    @Bean(name = "chTransactionManager")
    @Primary
    public DataSourceTransactionManager adsTransactionManager(@Qualifier("chDataSource") DataSource adsDataSource) {
        return new DataSourceTransactionManager(adsDataSource);
    }
 
    @Bean(name = "chSqlSessionFactory")
    @Primary
    public SqlSessionFactory adsSqlSessionFactory(@Qualifier("chDataSource") DataSource adsDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/ch/*Mapper.xml"));
        sessionFactory.setDataSource(adsDataSource);
        return sessionFactory.getObject();
    }
}
