package com.goldcard.usmart.domain.report;

import java.util.StringJoiner;

/**
 * @author 2299
 * @version 1.0 2018-11-12
 */
public class LoginInfo {

  private String tenantId;
  private String orgId;

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

  @Override
  public String toString() {
    return new StringJoiner(", ", LoginInfo.class.getSimpleName() + "[", "]")
        .add("tenantId='" + tenantId + "'")
        .add("orgId='" + orgId + "'")
        .toString();
  }
}
