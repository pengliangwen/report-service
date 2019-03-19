package com.goldcard.usmart.enums;

import java.io.File;

import com.goldcard.usmart.service.ReportBuilderTemplate;
import com.goldcard.usmart.service.impl.CollectionRecordReport;

/**
 * @author 2299
 * @version 1.0 2018-11-07
 */
public enum ReportTypeEnum {

  COLLECTION_RECORD("collectionRecord", "采集记录数据报表", CollectionRecordReport.class);


  private final String type;
  private final String name;
  private final Class<? extends ReportBuilderTemplate> template;

  ReportTypeEnum(String type, String name, Class<? extends ReportBuilderTemplate> template) {
    this.type = type;
    this.name = name;
    this.template = template;
  }

  public Class<? extends ReportBuilderTemplate> getTemplate() {
    return template;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public static ReportTypeEnum findByType(String reportType) {
    for (ReportTypeEnum one : ReportTypeEnum.values()) {
      if (one.getType().equals(reportType)) {
        return one;
      }
    }
    throw new IllegalArgumentException("报表类型不存在");
  }

  public static String getNameByFile(File file) {
    String name = file.getName();
    int start = name.lastIndexOf("-") + 1;
    int end = name.lastIndexOf(".");
    return findByType(name.substring(start, end)).getName() + ".xlsx";
  }

  public String buildFileName(Long taskId, String tenantId) {
    return  "[" + taskId + "]-[" + tenantId + "]-" + this.getType() + ".xlsx";
  }
}
