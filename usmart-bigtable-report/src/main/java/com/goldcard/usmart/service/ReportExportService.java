package com.goldcard.usmart.service;

import java.io.File;
import java.util.Optional;

import org.apache.tomcat.websocket.AuthenticationException;

import com.goldcard.usmart.domain.report.LoginInfo;
import com.goldcard.usmart.domain.report.ReportExportTasksReq;
import com.goldcard.usmart.domain.report.ReportExportTasksRes;
import com.goldcard.usmart.domain.report.SubmitTaskRes;

/**
 * @author 2299
 * @version 1.0 2018-11-06
 */
public interface ReportExportService {

  SubmitTaskRes submitReportExportTask(String reportType, String dto) throws Exception;

  ReportExportTasksRes findTaskList(ReportExportTasksReq req);

  Optional<File> downloadReport(Long task, LoginInfo s);

  LoginInfo getLoginInfo(String cookie) throws AuthenticationException;
}
