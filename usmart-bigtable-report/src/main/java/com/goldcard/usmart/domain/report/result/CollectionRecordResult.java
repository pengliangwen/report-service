package com.goldcard.usmart.domain.report.result;

import java.math.BigDecimal;
import java.util.Date;

public class CollectionRecordResult{

	private String eqNo;
    private String customerName;
    private Date createTime;
    private Date dataDate;
    private Date meterTime;
    private String standardSum;
    private String workingSum;
    private String balanceQuantity;
    private String standardFlow;
    private String workingFlow;
    private String temperature;
    private String pressure;
	public String getEqNo() {
		return eqNo;
	}
	public void setEqNo(String eqNo) {
		this.eqNo = eqNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getDataDate() {
		return dataDate;
	}
	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	public Date getMeterTime() {
		return meterTime;
	}
	public void setMeterTime(Date meterTime) {
		this.meterTime = meterTime;
	}
	public String getStandardSum() {
		return standardSum;
	}
	public void setStandardSum(String standardSum) {
		this.standardSum = standardSum;
	}
	public String getWorkingSum() {
		return workingSum;
	}
	public void setWorkingSum(String workingSum) {
		this.workingSum = workingSum;
	}
	public String getBalanceQuantity() {
		return balanceQuantity;
	}
	public void setBalanceQuantity(String balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}
	public String getStandardFlow() {
		return standardFlow;
	}
	public void setStandardFlow(String standardFlow) {
		this.standardFlow = standardFlow;
	}
	public String getWorkingFlow() {
		return workingFlow;
	}
	public void setWorkingFlow(String workingFlow) {
		this.workingFlow = workingFlow;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
}
