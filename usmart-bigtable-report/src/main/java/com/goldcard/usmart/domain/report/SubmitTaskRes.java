package com.goldcard.usmart.domain.report;

import java.util.StringJoiner;

/**
 * @author 2299
 * @version 1.0 2018-12-24
 */
public class SubmitTaskRes {

  private Long taskId;
  private Long hitRows;
  private String errorMsg;

  public SubmitTaskRes() {
  }

  public SubmitTaskRes(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public SubmitTaskRes(Long taskId, Long hitRows) {
    this.taskId = taskId;
    this.hitRows = hitRows;
  }

  public Long getTaskId() {
    return taskId;
  }

  public void setTaskId(Long taskId) {
    this.taskId = taskId;
  }

  public Long getHitRows() {
    return hitRows;
  }

  public void setHitRows(Long hitRows) {
    this.hitRows = hitRows;
  }

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SubmitTaskRes.class.getSimpleName() + "[", "]")
        .add("taskId=" + taskId)
        .add("hitRows=" + hitRows)
        .add("errorMsg='" + errorMsg + "'")
        .toString();
  }
}
