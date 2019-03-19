package com.goldcard.usmart.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goldcard.usmart.domain.report.LoginInfo;
import com.goldcard.usmart.domain.report.ReportExportTasksReq;
import com.goldcard.usmart.domain.report.ReportExportTasksRes;
import com.goldcard.usmart.domain.report.SubmitTaskRes;
import com.goldcard.usmart.enums.ReportTypeEnum;
import com.goldcard.usmart.service.ReportExportService;

/**
 * @author 2299
 * @version 1.0 2018-11-06
 */
@RestController
@RequestMapping("api/v2/reportExport")
public class ReportExportController {

  private final ReportExportService exportService;
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  @Value("${report.file-path}")
  private String filePath;

  @Autowired
  public ReportExportController(ReportExportService exportService) {
    this.exportService = exportService;
  }

  @PostMapping("submit/{reportType}")
  public ResponseEntity<SubmitTaskRes> submitExportTask(
      @PathVariable("reportType") String reportType, @RequestBody String requestBody) {
    try {
      log.info("接收任务 getReportType:{}, requestBody:{}", reportType, requestBody);
      return new ResponseEntity<>(exportService.submitReportExportTask(reportType, requestBody),
          HttpStatus.CREATED);
    }catch (IllegalArgumentException e) {
        log.error("请求内容异常：{}", e.toString());
        return new ResponseEntity<>(new SubmitTaskRes(e.getMessage()), HttpStatus.BAD_REQUEST);
    }catch (Exception e) {
      log.error("服务内部错误：", e);
      return new ResponseEntity<>(new SubmitTaskRes(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 任务列表查询
   */
  @PostMapping("taskList")
  public ReportExportTasksRes findTaskList(@RequestBody ReportExportTasksReq req) {
    return exportService.findTaskList(req);
  }

  /**
   * 报表下载
   */
  @GetMapping("download/{taskId}")
  public ResponseEntity<InputStreamResource> downLoadReport(@PathVariable("taskId") Long taskId,
      HttpServletRequest request) throws IOException {
    // 校验下载权限
    LoginInfo loginInfo = checkPermission(request);
    if (loginInfo == null) {
      log.warn("未授权的报表下载请求 ip:{} taskId:{}", getClientIP(request), taskId);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    // 获取下载文件
    Optional<File> reportFile = exportService.downloadReport(taskId, loginInfo);
    if (!reportFile.isPresent()) {
      log.error("获取报表文件失败 taskId:{} loginInfo:{}", taskId, loginInfo);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    // 响应文件字节流
    String urlFileName = URLEncoder.encode(ReportTypeEnum.getNameByFile(reportFile.get()), "UTF-8");
    HttpHeaders headers = getHttpHeaders(urlFileName);
    FileSystemResource file = new FileSystemResource(reportFile.get());
    return ResponseEntity.ok().headers(headers).contentLength(file.contentLength())
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(new InputStreamResource(file.getInputStream()));
  }

  private HttpHeaders getHttpHeaders(String urlFileName) {
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
    String disposition = String.format("attachment; filename=\"%s\"", urlFileName);
    headers.add(HttpHeaders.CONTENT_DISPOSITION, disposition);
    headers.add(HttpHeaders.PRAGMA, "no-cache");
    headers.add(HttpHeaders.EXPIRES, "0");
    return headers;
  }

  private LoginInfo checkPermission(HttpServletRequest request) {
    String header = request.getHeader(HttpHeaders.COOKIE);
    if (header == null) {
      log.error("下载报表时无法获取正确的 cookie");
      return null;
    }
    Optional<String> shiroCookie = Arrays.stream(StringUtils.trimAllWhitespace(header).split(";"))
        .filter(s -> s.startsWith("shiro.sesssion")).findFirst();
    if (!shiroCookie.isPresent()) {
      log.error("下载报表时无法获取正确的 cookie");
      return null;
    }
    LoginInfo loginInfo;
    try {
      loginInfo = exportService.getLoginInfo(shiroCookie.get());
    } catch (Exception e) {
      log.error("下载报表时无法获取 LoginInfo", e);
      return null;
    }
    return loginInfo;
  }

  private String getClientIP(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("X-Real-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }
}
