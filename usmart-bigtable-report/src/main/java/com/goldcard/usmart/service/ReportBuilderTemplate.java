package com.goldcard.usmart.service;

import org.apache.poi.ss.usermodel.Sheet;
import org.mybatis.spring.batch.MyBatisCursorItemReader;

import com.goldcard.usmart.domain.report.query.QueryDTO;
import com.goldcard.usmart.domain.report.result.ResultDTO;
import com.goldcard.usmart.enums.ReportTypeEnum;

/**
 * @author 2299
 * @version 1.0 2019-01-24
 */
public interface ReportBuilderTemplate<T extends QueryDTO, K extends ResultDTO> {

  int buildReportHead(Sheet sh);

  void buildDateRow(Sheet sh, int rowIndex, K resultDTO);

  void buildReportFoot(Sheet sh, int rowIndex);

  T convertAndVerify(String json);

  long count();

  MyBatisCursorItemReader<K> getRead();

  ReportTypeEnum getReportType();

  T getQueryDTO();

  void setQueryDTO(T queryDTO);
}
