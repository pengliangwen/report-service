package com.goldcard.usmart.redis;

/**
 * ----------------------------------------------------------------
 * Copyright (C) 2017 金卡高科技股份有限公司
 * 版权所有。 
 * 
 * 文件名：RedisKeyEnum.java
 * 文件功能描述：TODO
 * 
 * 创建标识 1990 2019年3月13日
 * 
 * 修改标识：
 * 修改描述：
 * ----------------------------------------------------------------
*/
public enum RedisKeyEnum {
	
	METER_ID_NAME("METER_ID_NAME_","设备对应客户名称");
	

	private String code;
	
	private String remark;

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

	private RedisKeyEnum(String code, String remark) {
		this.code = code;
		this.remark = remark;
	}

}
