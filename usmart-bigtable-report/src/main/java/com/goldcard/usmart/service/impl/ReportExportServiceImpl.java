package com.goldcard.usmart.service.impl;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.websocket.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.goldcard.usmart.dao.mysql.ReportExportTaskMapper;
import com.goldcard.usmart.domain.report.LoginInfo;
import com.goldcard.usmart.domain.report.ReportExportTask;
import com.goldcard.usmart.domain.report.ReportExportTasksReq;
import com.goldcard.usmart.domain.report.ReportExportTasksRes;
import com.goldcard.usmart.domain.report.SubmitTaskRes;
import com.goldcard.usmart.domain.report.TaskStatusEnum;
import com.goldcard.usmart.domain.report.query.QueryDTO;
import com.goldcard.usmart.enums.ReportTypeEnum;
import com.goldcard.usmart.redis.RedisService;
import com.goldcard.usmart.service.ReportBuilderTemplate;
import com.goldcard.usmart.service.ReportExportService;
import com.goldcard.usmart.service.ReportGenerator;
import com.goldcard.usmart.service.ReportLazyCleaner;

/**
 * @author 2299
 * @version 1.0 2018-11-06
 */
@Component
public class ReportExportServiceImpl implements ReportExportService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private static final String TASK_KEY = "report_export_";
  @Autowired
  ReportGenerator reportGenerator;
  @Autowired
  ReportLazyCleaner cleaner;
  @Autowired
  ApplicationContext context;
  @Value("${report.tenant-task-limit}")
  private Integer tenantTaskLimit;
  @Value("${report.file-path}")
  private String filePath;
  @Value("${express.url}")
  private String expressUrl;
  @Value("${report.clean-after-download}")
  private Integer cleanAfterDownload;
  
  @Value("${report.collectionRecordCount}")
  private Integer collectionRecordCount;
  @Autowired
  RedisService redisService;

  @Autowired
  private ReportExportTaskMapper reportExportTaskMapper;

  @Override
  @SuppressWarnings("unchecked")
  public SubmitTaskRes submitReportExportTask(String reportType, String dto) {
    // Redis 限流控制
    String key = TASK_KEY + dto.hashCode();
    String execute = (String) redisService.get(key);
    if (execute!=null) {
      log.error("重复提交相同任务：dto:{}", dto);
      throw new IllegalArgumentException("请勿重复提交相同任务");
    }
    redisService.set(key, "1",5);
    String beanName = ReportTypeEnum.findByType(reportType).getTemplate().getAnnotation(Component.class).value();
    ReportBuilderTemplate template = context.getBean(beanName, ReportBuilderTemplate.class);
    QueryDTO queryDTO = template.convertAndVerify(dto);
    // 租户并行任务控制
    if (tenantTaskLimit != null && tenantTaskLimit != -1) {
      long count = reportExportTaskMapper
          .countByTaskStatus(TaskStatusEnum.PROCESSING.getStatus(), queryDTO.getTenantId(), queryDTO.getReportType());
      if (count >= tenantTaskLimit) {
        throw new IllegalArgumentException("当前进行的任务过多：" + count);
      }
    }
    // 数据量控制
    long maxExportLimit = collectionRecordCount.longValue();
    long count = template.count();
    if (count == 0) {
      throw new IllegalArgumentException("该查询条件下无数据");
    } else if (maxExportLimit != -1 && count > maxExportLimit) {
      throw new IllegalArgumentException("已超出最大导出量，请缩小范围！");
    }
    // 创建并提交任务
    ReportExportTask task = new ReportExportTask();
    task.setReportType(reportType);
    task.setOrgId(queryDTO.getOrgId().replace("%", ""));
    task.setTenantId(queryDTO.getTenantId());
    task.setStaffId(queryDTO.getStaffId());
    task.setTaskStatus(TaskStatusEnum.PROCESSING.getStatus());
    task.setQueryConditions(dto);
    reportExportTaskMapper.insert(task);
    try {
      reportGenerator.generate(task.getId(), queryDTO, template);
    } catch (TaskRejectedException e) {
      log.warn("当前任务池已满，拒绝提交新的任务 {}", e.toString());
      throw new IllegalArgumentException("拒绝提交新的任务");
    }
    return new SubmitTaskRes(task.getId(), count);
  }

  @Override
  @Transactional
  public ReportExportTasksRes findTaskList(ReportExportTasksReq req) {
	  try{
    if (req.getSort().length() <= 4 && req.getSortName().length() <= 10) {
      req.setOrgId(req.getOrgId() + "%");
      long total = reportExportTaskMapper.countByReq(req);
      List<ReportExportTask> pageByReq = reportExportTaskMapper.findPageByReq(req);
      return new ReportExportTasksRes(total, pageByReq);
    } else {
      log.warn("查询参数存在SQL注入危险：{}", req);
      throw new IllegalArgumentException("查询参数存在SQL注入危险");
    }}catch(Exception e){
    	e.printStackTrace();
    }
	return null;
  }

  @Override
  public Optional<File> downloadReport(Long taskId, LoginInfo loginInfo) {
    ReportExportTask reportExportTask = reportExportTaskMapper
        .findCompleteTask(taskId, loginInfo.getOrgId() + "%", loginInfo.getTenantId());
    if (reportExportTask == null || StringUtils.isEmpty(reportExportTask.getFileName())) {
      return Optional.empty();
    } else {
      File file = new File(filePath, reportExportTask.getFileName());
      if (file.exists() && cleanAfterDownload >= 0) {
        cleaner.delayClean(cleanAfterDownload, taskId, file.getPath());
        return Optional.of(file);
      } else {
        return Optional.empty();
      }
    }
  }

  @Override
  public LoginInfo getLoginInfo(String cookie) throws AuthenticationException {
	RestTemplate restTemplate=new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.COOKIE, cookie);
    HttpEntity<String> request = new HttpEntity<>(null, httpHeaders);
    ResponseEntity<LoginInfo> exchange = restTemplate
        .exchange(expressUrl + "/login/getLoginInfo", HttpMethod.GET, request, LoginInfo.class);
    if (!exchange.getStatusCode().equals(HttpStatus.OK) || exchange.getBody() == null) {
      throw new AuthenticationException("获取登录信息失败");
    }
    return exchange.getBody();
  }
}
