package com.goldcard.usmart.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * ----------------------------------------------------------------
 * Copyright (C) 2017 金卡高科技股份有限公司
 * 版权所有。 
 * 
 * 文件名：PersistenceMsgType.java
 * 文件功能描述：持久化消息类型
 * 
 * 创建标识 1990 2017年5月25日
 * 
 * 修改标识：
 * 修改描述：
 * ----------------------------------------------------------------
*/
public enum PersistenceMsgType {
	
	COLLECTION_RECORD("collectionRecord","采集记录"),
	DAILY_RCORD("dailyRecord","日用量记录"),
	METER_AMOUNT_RECORD("meterAmountRecord","表用量记录");
	
	private String code;
	
	private String remark;

	
	
	private PersistenceMsgType(String code, String remark) {
		this.code = code;
		this.remark = remark;
	}

	
	public static PersistenceMsgType getPersistenceType(String code){
		for(PersistenceMsgType type:PersistenceMsgType.values()){
			if(StringUtils.equals(type.getCode(), code)){
				return type;
			}
		}
		return null;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	

}
