package com.goldcard.usmart.domain.report;


import java.util.StringJoiner;

/**
 * @author 2299
 * @version 1.0 2018-11-06
 */
public class ReportExportTasksReq extends PageSortReq {

  private String reportType;
  private String tenantId;
  private String orgId;

  @Override
  public String toString() {
    return new StringJoiner(", ", ReportExportTasksReq.class.getSimpleName() + "[", "]")
        .add("getReportType='" + reportType + "'")
        .add("tenantId='" + tenantId + "'")
        .add("orgId='" + orgId + "'")
        .toString();
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

  public String getReportType() {
    return reportType;
  }

  public void setReportType(String reportType) {
    this.reportType = reportType;
  }
}
