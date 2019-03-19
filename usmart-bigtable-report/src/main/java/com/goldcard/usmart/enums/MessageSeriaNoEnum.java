package com.goldcard.usmart.enums;

/**
 * ----------------------------------------------------------------
 * Copyright (C) 2017 金卡高科技股份有限公司
 * 版权所有。 
 * 
 * 文件名：MessageSeriaNoEnum.java
 * 文件功能描述：TODO
 * 
 * 创建标识 1990 2017年5月18日
 * 
 * 修改标识：
 * 修改描述：
 * ----------------------------------------------------------------
*/
public enum MessageSeriaNoEnum {
	
	DEVICE_ONLINE_SN("deviceOnlineSN"),
	MESSAGE_SAVEDOWN_SN("messageSaveDownSN"),
	MESSAGE_SAVEUP_SN("messageSaveUpSN"),
	SEND_AUTHORITY_SN("sendAuthoritySN"),
	BUSINESS_SN("businessSN"),
	PERSISTENCE_SN("persistenceSerialSN");
	
	
	private String key;

	private MessageSeriaNoEnum(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	

}
