package com.goldcard.usmart.domain.report;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class ReportExportTask {

  private Long id;
  private String staffId;
  private String tenantId;
  private String orgId;
  private Date createTime;
  private Date modifyTime;
  private String taskStatus;
  private String fileName;
  private String reportType;
  private String queryConditions;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStaffId() {
    return staffId;
  }

  public void setStaffId(String staffId) {
    this.staffId = staffId;
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

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getModifyTime() {
    return modifyTime;
  }

  public void setModifyTime(Date modifyTime) {
    this.modifyTime = modifyTime;
  }

  public String getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(String taskStatus) {
    this.taskStatus = taskStatus;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getReportType() {
    return reportType;
  }

  public void setReportType(String reportType) {
    this.reportType = reportType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReportExportTask that = (ReportExportTask) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(staffId, that.staffId) &&
        Objects.equals(tenantId, that.tenantId) &&
        Objects.equals(orgId, that.orgId) &&
        Objects.equals(createTime, that.createTime) &&
        Objects.equals(modifyTime, that.modifyTime) &&
        Objects.equals(taskStatus, that.taskStatus) &&
        Objects.equals(fileName, that.fileName) &&
        Objects.equals(reportType, that.reportType);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id, staffId, tenantId, orgId, createTime, modifyTime, taskStatus, fileName,
            reportType);
  }

  public String getQueryConditions() {
    return queryConditions;
  }

  public void setQueryConditions(String queryConditions) {
    this.queryConditions = queryConditions;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ReportExportTask.class.getSimpleName() + "[", "]")
        .add("id=" + id)
        .add("staffId='" + staffId + "'")
        .add("tenantId='" + tenantId + "'")
        .add("orgId='" + orgId + "'")
        .add("createTime=" + createTime)
        .add("modifyTime=" + modifyTime)
        .add("taskStatus='" + taskStatus + "'")
        .add("fileName='" + fileName + "'")
        .add("getReportType='" + reportType + "'")
        .add("queryConditions='" + queryConditions + "'")
        .toString();
  }
}