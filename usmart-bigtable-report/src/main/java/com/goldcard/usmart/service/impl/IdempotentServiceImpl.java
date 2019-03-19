package com.goldcard.usmart.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goldcard.usmart.redis.RedisService;
import com.goldcard.usmart.service.IdempotentService;

@Service
public class IdempotentServiceImpl implements IdempotentService {

	@Autowired
	RedisService redisService;
	/**
	 * 检测重复
	 * @param map
	 * @return
	 */
	public boolean checkRepeat(String serialNumber,String keyPre){
		String keyStr=keyPre+serialNumber;
		synchronized (keyStr.intern()) {
			if(StringUtils.isNotEmpty((String)redisService.get(keyStr))){
				return Boolean.TRUE;
			}else{
				//一天有效期
				redisService.set(keyStr,"1",24*60*60);
			}
		}
		return Boolean.FALSE;
	}
}
