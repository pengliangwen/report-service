package com.goldcard.usmart.domain.report;

import java.util.List;

/**
 * @author 2299
 * @version 1.0 2018-11-09
 */
public class ReportExportTasksRes {

  private Long allMatchCount;
  private List<ReportExportTask> data;

  public ReportExportTasksRes() {
  }

  public ReportExportTasksRes(Long allMatchCount,
      List<ReportExportTask> data) {
    this.allMatchCount = allMatchCount;
    this.data = data;
  }

  public Long getAllMatchCount() {
    return allMatchCount;
  }

  public void setAllMatchCount(Long allMatchCount) {
    this.allMatchCount = allMatchCount;
  }

  public List<ReportExportTask> getData() {
    return data;
  }

  public void setData(List<ReportExportTask> data) {
    this.data = data;
  }
}
