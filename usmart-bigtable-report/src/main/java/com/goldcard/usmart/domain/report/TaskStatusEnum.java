package com.goldcard.usmart.domain.report;

/**
 * @author 2299
 * @version 1.0 2018-11-08
 */
public enum TaskStatusEnum {

  PROCESSING("0", "处理中"),
  COMPLETE("1", "完成"),
  MISTAKE("2", "失败"),
  EXPIRED("3", "过期");

  private final String status;
  private final String info;

  TaskStatusEnum(String status, String info) {
    this.status = status;
    this.info = info;
  }

  public String getStatus() {
    return status;
  }

  public String getInfo() {
    return info;
  }
}
