package com.goldcard.usmart.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Meter {
    private String meterId;

    private String meterParamValue;

    private String mtId;

    private String meterNo;

    private Date assetDate;

    private Date expireDate;

    private String installStateId;

    private String addDes;

    private String userNo;

    private String customerName;

    private String customerType;

    private Date setUpTime;

    private String setUpLocation;

    private String gasEnvironment;

    private BigDecimal enableMeterReading;

    private String cardDirection;

    private String gasDirection;

    private String valveStateId;

    private Date valveStateTime;

    private BigDecimal meterReading;

    private Date meterReadingTime;

    private String meterParameter;

    private Date meterTime;

    private BigDecimal signalGrade;

    private BigDecimal currentCellVoltage;

    private String alertParamValue;

    private String meterAlarmValue;

    private String meterStateValue;

    private String priceId;

    private Long priceVersion;

    private String lastPriceId;

    private Long lastPriceVersion;

    private BigDecimal totalDebitQty;

    private Integer totalDebitNum;

    private BigDecimal totalDebitAmt;

    private BigDecimal lastDebitQty;

    private BigDecimal lastDebitAmt;

    private Date lastDebitDate;

    private BigDecimal meterBalanceAmt;

    private BigDecimal meterBalanceQty;

    private BigDecimal meterDebitAmt;

    private BigDecimal meterDebitQty;

    private Integer curPriceIndex;

    private Date dataDate;

    private BigDecimal lastBillRead;

    private Date cycleStartDay;

    private BigDecimal cycleStartRead;

    private Date cycleEndDay;

    private BigDecimal oldMeterQty;

    private String mobileCarrierId;

    private String simUim;

    private String localIp;

    private String localPort;

    private Integer version;

    private String orgId;

    private Date modifiedTime;

    private String tenantId;

    private String modifior;

    private Date createTime;

    private String creator;

    private String remarks;

    private String ptId;

    private String groupId;

    private String devNo;

    private String slaveNo;

    private BigDecimal curPrice;

    private String devType;

    private String onlineState;

    private String y;

    private String x;

    private Date meterCollectionDate;

    private Integer meterPricingNum;

    private String valveCtrlParamValue;

    private String priceParamValue;

    private String devPtid;

    private BigDecimal lastCollectionReading;

    private Date lastCollectionTime;

    private Integer respDelayTime;

    private String manuId;

    private Date priceEffectiveDate;

    private String eachSurplusQty;

    private String collectorNo;

    private String phoneNo;

    private String productMarking;

    private String protocolVersion;

    private String upgradeStatus;

    private String wechatNo;

    private String protocolparamBatchId;

    private String alarmparamBatchId;

    private String valvecontrolBatchId;

    private String openValveBatchId;

    private String openValveBatchState;

    private String closeValveBatchId;

    private String closeValveBatchState;

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId == null ? null : meterId.trim();
    }

    public String getMeterParamValue() {
        return meterParamValue;
    }

    public void setMeterParamValue(String meterParamValue) {
        this.meterParamValue = meterParamValue == null ? null : meterParamValue.trim();
    }

    public String getMtId() {
        return mtId;
    }

    public void setMtId(String mtId) {
        this.mtId = mtId == null ? null : mtId.trim();
    }

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo == null ? null : meterNo.trim();
    }

    public Date getAssetDate() {
        return assetDate;
    }

    public void setAssetDate(Date assetDate) {
        this.assetDate = assetDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getInstallStateId() {
        return installStateId;
    }

    public void setInstallStateId(String installStateId) {
        this.installStateId = installStateId == null ? null : installStateId.trim();
    }

    public String getAddDes() {
        return addDes;
    }

    public void setAddDes(String addDes) {
        this.addDes = addDes == null ? null : addDes.trim();
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo == null ? null : userNo.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : customerType.trim();
    }

    public Date getSetUpTime() {
        return setUpTime;
    }

    public void setSetUpTime(Date setUpTime) {
        this.setUpTime = setUpTime;
    }

    public String getSetUpLocation() {
        return setUpLocation;
    }

    public void setSetUpLocation(String setUpLocation) {
        this.setUpLocation = setUpLocation == null ? null : setUpLocation.trim();
    }

    public String getGasEnvironment() {
        return gasEnvironment;
    }

    public void setGasEnvironment(String gasEnvironment) {
        this.gasEnvironment = gasEnvironment == null ? null : gasEnvironment.trim();
    }

    public BigDecimal getEnableMeterReading() {
        return enableMeterReading;
    }

    public void setEnableMeterReading(BigDecimal enableMeterReading) {
        this.enableMeterReading = enableMeterReading;
    }

    public String getCardDirection() {
        return cardDirection;
    }

    public void setCardDirection(String cardDirection) {
        this.cardDirection = cardDirection == null ? null : cardDirection.trim();
    }

    public String getGasDirection() {
        return gasDirection;
    }

    public void setGasDirection(String gasDirection) {
        this.gasDirection = gasDirection == null ? null : gasDirection.trim();
    }

    public String getValveStateId() {
        return valveStateId;
    }

    public void setValveStateId(String valveStateId) {
        this.valveStateId = valveStateId == null ? null : valveStateId.trim();
    }

    public Date getValveStateTime() {
        return valveStateTime;
    }

    public void setValveStateTime(Date valveStateTime) {
        this.valveStateTime = valveStateTime;
    }

    public BigDecimal getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(BigDecimal meterReading) {
        this.meterReading = meterReading;
    }

    public Date getMeterReadingTime() {
        return meterReadingTime;
    }

    public void setMeterReadingTime(Date meterReadingTime) {
        this.meterReadingTime = meterReadingTime;
    }

    public String getMeterParameter() {
        return meterParameter;
    }

    public void setMeterParameter(String meterParameter) {
        this.meterParameter = meterParameter == null ? null : meterParameter.trim();
    }

    public Date getMeterTime() {
        return meterTime;
    }

    public void setMeterTime(Date meterTime) {
        this.meterTime = meterTime;
    }

    public BigDecimal getSignalGrade() {
        return signalGrade;
    }

    public void setSignalGrade(BigDecimal signalGrade) {
        this.signalGrade = signalGrade;
    }

    public BigDecimal getCurrentCellVoltage() {
        return currentCellVoltage;
    }

    public void setCurrentCellVoltage(BigDecimal currentCellVoltage) {
        this.currentCellVoltage = currentCellVoltage;
    }

    public String getAlertParamValue() {
        return alertParamValue;
    }

    public void setAlertParamValue(String alertParamValue) {
        this.alertParamValue = alertParamValue == null ? null : alertParamValue.trim();
    }

    public String getMeterAlarmValue() {
        return meterAlarmValue;
    }

    public void setMeterAlarmValue(String meterAlarmValue) {
        this.meterAlarmValue = meterAlarmValue == null ? null : meterAlarmValue.trim();
    }

    public String getMeterStateValue() {
        return meterStateValue;
    }

    public void setMeterStateValue(String meterStateValue) {
        this.meterStateValue = meterStateValue == null ? null : meterStateValue.trim();
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId == null ? null : priceId.trim();
    }

    public Long getPriceVersion() {
        return priceVersion;
    }

    public void setPriceVersion(Long priceVersion) {
        this.priceVersion = priceVersion;
    }

    public String getLastPriceId() {
        return lastPriceId;
    }

    public void setLastPriceId(String lastPriceId) {
        this.lastPriceId = lastPriceId == null ? null : lastPriceId.trim();
    }

    public Long getLastPriceVersion() {
        return lastPriceVersion;
    }

    public void setLastPriceVersion(Long lastPriceVersion) {
        this.lastPriceVersion = lastPriceVersion;
    }

    public BigDecimal getTotalDebitQty() {
        return totalDebitQty;
    }

    public void setTotalDebitQty(BigDecimal totalDebitQty) {
        this.totalDebitQty = totalDebitQty;
    }

    public Integer getTotalDebitNum() {
        return totalDebitNum;
    }

    public void setTotalDebitNum(Integer totalDebitNum) {
        this.totalDebitNum = totalDebitNum;
    }

    public BigDecimal getTotalDebitAmt() {
        return totalDebitAmt;
    }

    public void setTotalDebitAmt(BigDecimal totalDebitAmt) {
        this.totalDebitAmt = totalDebitAmt;
    }

    public BigDecimal getLastDebitQty() {
        return lastDebitQty;
    }

    public void setLastDebitQty(BigDecimal lastDebitQty) {
        this.lastDebitQty = lastDebitQty;
    }

    public BigDecimal getLastDebitAmt() {
        return lastDebitAmt;
    }

    public void setLastDebitAmt(BigDecimal lastDebitAmt) {
        this.lastDebitAmt = lastDebitAmt;
    }

    public Date getLastDebitDate() {
        return lastDebitDate;
    }

    public void setLastDebitDate(Date lastDebitDate) {
        this.lastDebitDate = lastDebitDate;
    }

    public BigDecimal getMeterBalanceAmt() {
        return meterBalanceAmt;
    }

    public void setMeterBalanceAmt(BigDecimal meterBalanceAmt) {
        this.meterBalanceAmt = meterBalanceAmt;
    }

    public BigDecimal getMeterBalanceQty() {
        return meterBalanceQty;
    }

    public void setMeterBalanceQty(BigDecimal meterBalanceQty) {
        this.meterBalanceQty = meterBalanceQty;
    }

    public BigDecimal getMeterDebitAmt() {
        return meterDebitAmt;
    }

    public void setMeterDebitAmt(BigDecimal meterDebitAmt) {
        this.meterDebitAmt = meterDebitAmt;
    }

    public BigDecimal getMeterDebitQty() {
        return meterDebitQty;
    }

    public void setMeterDebitQty(BigDecimal meterDebitQty) {
        this.meterDebitQty = meterDebitQty;
    }

    public Integer getCurPriceIndex() {
        return curPriceIndex;
    }

    public void setCurPriceIndex(Integer curPriceIndex) {
        this.curPriceIndex = curPriceIndex;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public BigDecimal getLastBillRead() {
        return lastBillRead;
    }

    public void setLastBillRead(BigDecimal lastBillRead) {
        this.lastBillRead = lastBillRead;
    }

    public Date getCycleStartDay() {
        return cycleStartDay;
    }

    public void setCycleStartDay(Date cycleStartDay) {
        this.cycleStartDay = cycleStartDay;
    }

    public BigDecimal getCycleStartRead() {
        return cycleStartRead;
    }

    public void setCycleStartRead(BigDecimal cycleStartRead) {
        this.cycleStartRead = cycleStartRead;
    }

    public Date getCycleEndDay() {
        return cycleEndDay;
    }

    public void setCycleEndDay(Date cycleEndDay) {
        this.cycleEndDay = cycleEndDay;
    }

    public BigDecimal getOldMeterQty() {
        return oldMeterQty;
    }

    public void setOldMeterQty(BigDecimal oldMeterQty) {
        this.oldMeterQty = oldMeterQty;
    }

    public String getMobileCarrierId() {
        return mobileCarrierId;
    }

    public void setMobileCarrierId(String mobileCarrierId) {
        this.mobileCarrierId = mobileCarrierId == null ? null : mobileCarrierId.trim();
    }

    public String getSimUim() {
        return simUim;
    }

    public void setSimUim(String simUim) {
        this.simUim = simUim == null ? null : simUim.trim();
    }

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp == null ? null : localIp.trim();
    }

    public String getLocalPort() {
        return localPort;
    }

    public void setLocalPort(String localPort) {
        this.localPort = localPort == null ? null : localPort.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getModifior() {
        return modifior;
    }

    public void setModifior(String modifior) {
        this.modifior = modifior == null ? null : modifior.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getPtId() {
        return ptId;
    }

    public void setPtId(String ptId) {
        this.ptId = ptId == null ? null : ptId.trim();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getDevNo() {
        return devNo;
    }

    public void setDevNo(String devNo) {
        this.devNo = devNo == null ? null : devNo.trim();
    }

    public String getSlaveNo() {
        return slaveNo;
    }

    public void setSlaveNo(String slaveNo) {
        this.slaveNo = slaveNo == null ? null : slaveNo.trim();
    }

    public BigDecimal getCurPrice() {
        return curPrice;
    }

    public void setCurPrice(BigDecimal curPrice) {
        this.curPrice = curPrice;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType == null ? null : devType.trim();
    }

    public String getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(String onlineState) {
        this.onlineState = onlineState == null ? null : onlineState.trim();
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y == null ? null : y.trim();
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x == null ? null : x.trim();
    }

    public Date getMeterCollectionDate() {
        return meterCollectionDate;
    }

    public void setMeterCollectionDate(Date meterCollectionDate) {
        this.meterCollectionDate = meterCollectionDate;
    }

    public Integer getMeterPricingNum() {
        return meterPricingNum;
    }

    public void setMeterPricingNum(Integer meterPricingNum) {
        this.meterPricingNum = meterPricingNum;
    }

    public String getValveCtrlParamValue() {
        return valveCtrlParamValue;
    }

    public void setValveCtrlParamValue(String valveCtrlParamValue) {
        this.valveCtrlParamValue = valveCtrlParamValue == null ? null : valveCtrlParamValue.trim();
    }

    public String getPriceParamValue() {
        return priceParamValue;
    }

    public void setPriceParamValue(String priceParamValue) {
        this.priceParamValue = priceParamValue == null ? null : priceParamValue.trim();
    }

    public String getDevPtid() {
        return devPtid;
    }

    public void setDevPtid(String devPtid) {
        this.devPtid = devPtid == null ? null : devPtid.trim();
    }

    public BigDecimal getLastCollectionReading() {
        return lastCollectionReading;
    }

    public void setLastCollectionReading(BigDecimal lastCollectionReading) {
        this.lastCollectionReading = lastCollectionReading;
    }

    public Date getLastCollectionTime() {
        return lastCollectionTime;
    }

    public void setLastCollectionTime(Date lastCollectionTime) {
        this.lastCollectionTime = lastCollectionTime;
    }

    public Integer getRespDelayTime() {
        return respDelayTime;
    }

    public void setRespDelayTime(Integer respDelayTime) {
        this.respDelayTime = respDelayTime;
    }

    public String getManuId() {
        return manuId;
    }

    public void setManuId(String manuId) {
        this.manuId = manuId == null ? null : manuId.trim();
    }

    public Date getPriceEffectiveDate() {
        return priceEffectiveDate;
    }

    public void setPriceEffectiveDate(Date priceEffectiveDate) {
        this.priceEffectiveDate = priceEffectiveDate;
    }

    public String getEachSurplusQty() {
        return eachSurplusQty;
    }

    public void setEachSurplusQty(String eachSurplusQty) {
        this.eachSurplusQty = eachSurplusQty == null ? null : eachSurplusQty.trim();
    }

    public String getCollectorNo() {
        return collectorNo;
    }

    public void setCollectorNo(String collectorNo) {
        this.collectorNo = collectorNo == null ? null : collectorNo.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getProductMarking() {
        return productMarking;
    }

    public void setProductMarking(String productMarking) {
        this.productMarking = productMarking == null ? null : productMarking.trim();
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion == null ? null : protocolVersion.trim();
    }

    public String getUpgradeStatus() {
        return upgradeStatus;
    }

    public void setUpgradeStatus(String upgradeStatus) {
        this.upgradeStatus = upgradeStatus == null ? null : upgradeStatus.trim();
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo == null ? null : wechatNo.trim();
    }

    public String getProtocolparamBatchId() {
        return protocolparamBatchId;
    }

    public void setProtocolparamBatchId(String protocolparamBatchId) {
        this.protocolparamBatchId = protocolparamBatchId == null ? null : protocolparamBatchId.trim();
    }

    public String getAlarmparamBatchId() {
        return alarmparamBatchId;
    }

    public void setAlarmparamBatchId(String alarmparamBatchId) {
        this.alarmparamBatchId = alarmparamBatchId == null ? null : alarmparamBatchId.trim();
    }

    public String getValvecontrolBatchId() {
        return valvecontrolBatchId;
    }

    public void setValvecontrolBatchId(String valvecontrolBatchId) {
        this.valvecontrolBatchId = valvecontrolBatchId == null ? null : valvecontrolBatchId.trim();
    }

    public String getOpenValveBatchId() {
        return openValveBatchId;
    }

    public void setOpenValveBatchId(String openValveBatchId) {
        this.openValveBatchId = openValveBatchId == null ? null : openValveBatchId.trim();
    }

    public String getOpenValveBatchState() {
        return openValveBatchState;
    }

    public void setOpenValveBatchState(String openValveBatchState) {
        this.openValveBatchState = openValveBatchState == null ? null : openValveBatchState.trim();
    }

    public String getCloseValveBatchId() {
        return closeValveBatchId;
    }

    public void setCloseValveBatchId(String closeValveBatchId) {
        this.closeValveBatchId = closeValveBatchId == null ? null : closeValveBatchId.trim();
    }

    public String getCloseValveBatchState() {
        return closeValveBatchState;
    }

    public void setCloseValveBatchState(String closeValveBatchState) {
        this.closeValveBatchState = closeValveBatchState == null ? null : closeValveBatchState.trim();
    }
}