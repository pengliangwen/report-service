package com.goldcard.usmart.mq;



/**
 * ----------------------------------------------------------------
 * Copyright (C) 2017 金卡高科技股份有限公司
 * 版权所有。 
 * 
 * 文件名：MessageProducer.java
 * 文件功能描述：TODO
 * 
 * 创建标识 1990 2019年3月6日
 * 
 * 修改标识：
 * 修改描述：
 * ----------------------------------------------------------------
*/
public interface MessageProducer {
	
	public void sendToBizMq(String topic,String tag,String message);
	
	public void sendToBizMq(String topic,String tag,String message,int second);

}
