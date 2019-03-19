package com.goldcard.usmart.utils;

import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author 2299
 * @version 1.0 2018-11-07
 */
@Component
public class CursorReaderBuilder {

  @Autowired
  SqlSessionFactory chSqlSessionFactory;



  public <T> MyBatisCursorItemReader<T> buildReader(Object param, String mapperId) {
    MyBatisCursorItemReader<T> reader = new MyBatisCursorItemReader<>();
    reader.setSqlSessionFactory(chSqlSessionFactory);
    ObjectMapper objectMapper=new ObjectMapper();
    reader.setParameterValues(
        objectMapper.convertValue(param, new TypeReference<Map<String, Object>>() {
        }));
    reader.setQueryId(mapperId);
    return reader;
  }
}
