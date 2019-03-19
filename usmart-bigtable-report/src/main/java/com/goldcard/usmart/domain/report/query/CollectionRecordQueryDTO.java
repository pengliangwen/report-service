package com.goldcard.usmart.domain.report.query;


import java.sql.Date;
import java.util.Objects;

public class CollectionRecordQueryDTO implements QueryDTO{
    // 表具编号
    private String eqNo;
    // 客户名称
    private  String customerName;
    // 开始 采集时间
    private String collectionTimeStart;
    // 结束 采集时间
    private String collectionTimeEnd;
    // 开始 温度
    private String temperatureStart;
   // 结束 温度
   private String temperatureEnd;
   // 开始 压力
   private String pressureStart;
   // 结束 压力
   private String pressureEnd;
   // 开始 标况瞬时流量
   private String standardFlowStart;
   // 结束 标况瞬时流量
   private String standardFlowEnd;
    // 报表类型
    private String reportType;

    private String tenantId;
    private String orgId;
    private String staffId;
    private String staffName;

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

   

    public String getCollectionTimeStart() {
		return collectionTimeStart;
	}

	public void setCollectionTimeStart(String collectionTimeStart) {
		this.collectionTimeStart = collectionTimeStart;
	}

	public String getCollectionTimeEnd() {
		return collectionTimeEnd;
	}

	public void setCollectionTimeEnd(String collectionTimeEnd) {
		this.collectionTimeEnd = collectionTimeEnd;
	}

	public String getTemperatureStart() {
        return temperatureStart;
    }

    public void setTemperatureStart(String temperatureStart) {
        this.temperatureStart = temperatureStart;
    }

    public String getTemperatureEnd() {
        return temperatureEnd;
    }

    public void setTemperatureEnd(String temperatureEnd) {
        this.temperatureEnd = temperatureEnd;
    }

    public String getPressureStart() {
        return pressureStart;
    }

    public void setPressureStart(String pressureStart) {
        this.pressureStart = pressureStart;
    }

    public String getPressureEnd() {
        return pressureEnd;
    }

    public void setPressureEnd(String pressureEnd) {
        this.pressureEnd = pressureEnd;
    }

    public String getStandardFlowStart() {
        return standardFlowStart;
    }

    public void setStandardFlowStart(String standardFlowStart) {
        this.standardFlowStart = standardFlowStart;
    }

    public String getStandardFlowEnd() {
        return standardFlowEnd;
    }

    public void setStandardFlowEnd(String standardFlowEnd) {
        this.standardFlowEnd = standardFlowEnd;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Override
    public String toString() {
        return "CollectionRecordQueryDTO{" +
                "eqNo='" + eqNo + '\'' +
                ", customerName='" + customerName + '\'' +
                ", collectionTimeStart=" + collectionTimeStart +
                ", collectionTimeEnd=" + collectionTimeEnd +
                ", temperatureStart='" + temperatureStart + '\'' +
                ", temperatureEnd='" + temperatureEnd + '\'' +
                ", pressureStart='" + pressureStart + '\'' +
                ", pressureEnd='" + pressureEnd + '\'' +
                ", standardFlowStart='" + standardFlowStart + '\'' +
                ", standardFlowEnd='" + standardFlowEnd + '\'' +
                ", reportType='" + reportType + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", orgId='" + orgId + '\'' +
                ", staffId='" + staffId + '\'' +
                ", staffName='" + staffName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollectionRecordQueryDTO that = (CollectionRecordQueryDTO) o;
        return Objects.equals(eqNo, that.eqNo) &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(collectionTimeStart, that.collectionTimeStart) &&
                Objects.equals(collectionTimeEnd, that.collectionTimeEnd) &&
                Objects.equals(temperatureStart, that.temperatureStart) &&
                Objects.equals(temperatureEnd, that.temperatureEnd) &&
                Objects.equals(pressureStart, that.pressureStart) &&
                Objects.equals(pressureEnd, that.pressureEnd) &&
                Objects.equals(standardFlowStart, that.standardFlowStart) &&
                Objects.equals(standardFlowEnd, that.standardFlowEnd) &&
                Objects.equals(reportType, that.reportType) &&
                Objects.equals(tenantId, that.tenantId) &&
                Objects.equals(orgId, that.orgId);
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(eqNo, customerName, collectionTimeStart, collectionTimeEnd, temperatureStart,
                        temperatureEnd, pressureStart, pressureEnd, standardFlowStart, standardFlowEnd, reportType, tenantId, orgId);
    }

}
