package com.goldcard.usmart.service;


public interface IdempotentService {
	
	/**
	 * 检测重复
	 * @param map
	 * @return
	 */
	public boolean checkRepeat(String serialNumber,String keyPre);

}
