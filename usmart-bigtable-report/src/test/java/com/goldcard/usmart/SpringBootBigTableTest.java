package com.goldcard.usmart;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.goldcard.usmart.dao.ch.CollectionRecordMapper;
import com.goldcard.usmart.domain.CollectionRecord;
import com.goldcard.usmart.domain.report.query.CollectionRecordQueryDTO;
import com.goldcard.usmart.domain.report.result.CollectionRecordResultDTO;
import com.goldcard.usmart.service.impl.CollectionRecordReport;
import com.goldcard.usmart.utils.DateFilterUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootBigTableTest 
{
	@Autowired
	CollectionRecordMapper collectionRecordMapper;

	private static Logger logger = LoggerFactory.getLogger(CollectionRecordReport.class);

	@Test
    public void queryCHCollectionRecord()
    {
    	CollectionRecordQueryDTO dtoquey=new CollectionRecordQueryDTO();
//    	dtoquey.setCollectionTimeStart(new Date(DateFilterUtil.parserYMD("2019-01-01").getTime()));
//    	dtoquey.setCollectionTimeEnd(new Date(DateFilterUtil.parserYMD("2019-01-03").getTime()));
    	
     	dtoquey.setCollectionTimeStart("2019-01-01");
    	dtoquey.setCollectionTimeEnd("2019-01-03");
    	dtoquey.setTenantId("0261");
    	dtoquey.setOrgId("990101%");
    	try{
    		long time=System.currentTimeMillis();
        	List<CollectionRecordResultDTO> list=collectionRecordMapper.findByQueryDTO(dtoquey);
        	logger.info("查询 clikchouse 采集记录,条数：{},时间：{}",list.size(),System.currentTimeMillis()-time);
    	}catch (Exception e){
    		logger.error(e.getMessage(),e);
    	}
    }
    
    public void insertCk(){
    	CollectionRecord record=new CollectionRecord();
    	record.setCrId("888888888");
    	record.setCreateTime(DateFilterUtil.parserYMD("2018-12-15"));
    	record.setDataDate(DateFilterUtil.parserYMD("2018-12-15"));
    	record.setMeterTime(DateFilterUtil.parserYMD("2018-12-15"));
    	record.setReadingTime(DateFilterUtil.parserYMD("2018-12-15"));
    	record.setCustomerName("test user name");
    	try{
    		record=(CollectionRecord) checkNull(record);
    		collectionRecordMapper.insertCollectionRecord(record);
    	}catch (Exception e){
    		logger.error(e.getMessage(),e);
    	}
    }
    
    public  Object checkNull(Object vo) throws Exception {
        // 获取实体类的所有属性，返回Field数组
        Field[] field = vo.getClass().getDeclaredFields();
        // 获取属性的名字
        for (int i = 0; i < field.length; i++) {
            //可访问私有变量
            field[i].setAccessible(true);
            // 获取属性的名字
            String name = field[i].getName();
            // 获取属性类型
            String type = field[i].getGenericType().toString();
            // 将属性的首字母大写
            name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1)
                    .toUpperCase());

            if (type.equals("class java.lang.String")) {
                // 如果type是类类型，则前面包含"class "，后面跟类名
                Method m = vo.getClass().getMethod("get" + name);
                // 调用getter方法获取属性值
                String value = (String) m.invoke(vo);
                if (!StringUtils.isNotBlank(value)) {
                    //给属性设值
                    field[i].set(vo, field[i].getType().getConstructor(field[i].getType()).newInstance(""));
                }
            }
        }
        return vo;
    }
}
