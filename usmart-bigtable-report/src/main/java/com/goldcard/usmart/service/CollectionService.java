package com.goldcard.usmart.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.goldcard.usmart.dao.ch.CollectionRecordMapper;
import com.goldcard.usmart.dao.mysql.MeterMapper;
import com.goldcard.usmart.domain.CollectionRecord;
import com.goldcard.usmart.domain.Meter;
import com.goldcard.usmart.enums.MessageSeriaNoEnum;
import com.goldcard.usmart.enums.PersistenceMsgType;
import com.goldcard.usmart.mq.rocketmq.PersistenceMsgConsumer;
import com.goldcard.usmart.redis.RedisKeyEnum;
import com.goldcard.usmart.redis.RedisService;


@Component
public class CollectionService {
	
	private static final Logger logger = LoggerFactory.getLogger(PersistenceMsgConsumer.class);


	@Autowired
	RedisService redisService;
	@Autowired
	MeterMapper meterMapper;
	@Autowired
	CollectionRecordMapper  collectionRecordMapper;
	@Autowired
	IdempotentService idempotentService;
	public void collectionPersist(String json){
		
		CollectionRecord record=JSONObject.parseObject(json, CollectionRecord.class);
		record.setCustomerName(getCustomerName(record.getEqId()));
		try {
			record=(CollectionRecord) checkNull(record);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		collectionRecordMapper.insertCollectionRecord(record);
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

	
	public void executor(List<String> list){
		
		if(list!=null &&list.size()>0){
			for(String str:list){
				JSONObject obj=JSONObject.parseObject(str);
				String msgType=(String) obj.get("persistMsgType");
				String jsonStr=(String) obj.get("message");
				String serialNumber=(String)obj.get("serialNumber");
				// 去重判断
				if(!idempotentService.checkRepeat(serialNumber,MessageSeriaNoEnum.PERSISTENCE_SN.getKey()))
					switch (PersistenceMsgType.getPersistenceType(msgType)) {
					case COLLECTION_RECORD :
						collectionPersist(jsonStr);
						break;
					default:
						break;
				}
			}
		}
		
	}
	

	
	
	public String  getCustomerName(String meterId){
		String customerName=(String)redisService.get(RedisKeyEnum.METER_ID_NAME+meterId);
		if(customerName==null){
			Meter meter=meterMapper.selectByPrimaryKey(meterId);
			customerName=meter!=null?meter.getCustomerName():"";
			if(customerName==null){
				customerName="";
			}
			redisService.set(RedisKeyEnum.METER_ID_NAME+meterId,customerName);
		}
		return customerName;	
	}
	
}
